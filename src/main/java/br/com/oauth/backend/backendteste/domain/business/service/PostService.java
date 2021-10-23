package br.com.oauth.backend.backendteste.domain.business.service;

import br.com.oauth.backend.backendteste.api.rest.v1.request.PostRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.MessageResponse;
import br.com.oauth.backend.backendteste.api.rest.v1.response.PostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {

    MessageResponse postPublic(PostRequest request, MultipartFile file);

    List<PostResponse> getAll();

    List<PostResponse> getByUserId();

}
