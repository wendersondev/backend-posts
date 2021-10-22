package br.com.oauth.backend.backendteste.domain.business.service.impl;

import br.com.oauth.backend.backendteste.api.rest.v1.request.PostRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.MessageResponse;
import br.com.oauth.backend.backendteste.domain.business.service.FilesStorageService;
import br.com.oauth.backend.backendteste.domain.business.service.PostService;
import br.com.oauth.backend.backendteste.infrastructure.entity.PostEntity;
import br.com.oauth.backend.backendteste.infrastructure.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public MessageResponse postPublic(PostRequest request, MultipartFile file) {

        filesStorageService.save(file);
        postRepository.save(PostEntity
                .builder()
                        .privatePost(request.getPrivatePost())
                        .obs(request.getObs())
                        .url(file.getOriginalFilename())
                .build());

        return MessageResponse
                .builder()
                .message("Post Salvo com sucesso com o arquivo ".concat(file.getOriginalFilename()))
                .build();

    }
}
