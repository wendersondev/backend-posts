package br.com.oauth.backend.backendteste.domain.business.service.impl;

import br.com.oauth.backend.backendteste.api.rest.v1.request.PostRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.MessageResponse;
import br.com.oauth.backend.backendteste.api.rest.v1.response.PostResponse;
import br.com.oauth.backend.backendteste.domain.business.service.FilesStorageService;
import br.com.oauth.backend.backendteste.domain.business.service.PostService;
import br.com.oauth.backend.backendteste.infrastructure.entity.PostEntity;
import br.com.oauth.backend.backendteste.infrastructure.entity.UserEntity;
import br.com.oauth.backend.backendteste.infrastructure.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private FilesStorageService filesStorageService;

    @Autowired
    private PostRepository postRepository;

    @Override
    public MessageResponse postPublic(PostRequest request, MultipartFile file) {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        filesStorageService.save(file);
        postRepository.save(PostEntity
                .builder()
                        .privatePost(request.getPrivatePost())
                        .obs(request.getObs())
                        .url(file.getOriginalFilename())
                        .userId(user.getId())
                .build());

        return MessageResponse
                .builder()
                .message("Post Salvo com sucesso com o arquivo ".concat(file.getOriginalFilename()))
                .build();

    }

    @Override
    public List<PostResponse> getAll() {
        return postRepository.findAll()
                .stream()
                .map(entity -> {
                    return PostResponse
                            .builder()
                            .privatePost(entity.getPrivatePost())
                            .id(entity.getId())
                            .obs(entity.getObs())
                            .url(entity.getUrl())
                            .build();
                }).collect(Collectors.toList());
    }

    @Override
    public List<PostResponse> getByUserId() {
        UserEntity user = (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
        return postRepository.findByUserId(user.getId())
                .stream()
                .map(entity -> {
                    return PostResponse
                            .builder()
                            .privatePost(entity.getPrivatePost())
                            .id(entity.getId())
                            .obs(entity.getObs())
                            .url(entity.getUrl())
                            .build();
                }).collect(Collectors.toList());
    }
}
