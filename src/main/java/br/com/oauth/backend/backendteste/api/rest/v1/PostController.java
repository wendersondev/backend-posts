package br.com.oauth.backend.backendteste.api.rest.v1;

import br.com.oauth.backend.backendteste.api.rest.v1.request.PostRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.FileInfoResponse;
import br.com.oauth.backend.backendteste.api.rest.v1.response.MessageResponse;
import br.com.oauth.backend.backendteste.domain.business.service.FilesStorageService;
import br.com.oauth.backend.backendteste.domain.business.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FilesStorageService storageService;

    @PostMapping
    public ResponseEntity<MessageResponse> save(@RequestParam("file") MultipartFile file,
                                                @RequestParam("privatePost") Boolean privatePost,
                                                @RequestParam("obs") String obs){
        log.info("Gerando um novo post");
        return new ResponseEntity<MessageResponse>(postService.postPublic(PostRequest.builder()
                        .privatePost(privatePost)
                        .obs(obs).build(),
                file), HttpStatus.OK);
    }

    @GetMapping("/files")
    public ResponseEntity<List<FileInfoResponse>> getListFiles() {
        List<FileInfoResponse> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(PostController.class, "getFile", path.getFileName().toString()).build().toString();

            return FileInfoResponse.builder().name(filename).url(url).build();
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
