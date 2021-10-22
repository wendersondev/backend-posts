package br.com.oauth.backend.backendteste.domain.business.service;

import br.com.oauth.backend.backendteste.api.rest.v1.request.PostRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.MessageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface PostService {

    MessageResponse postPublic(PostRequest request, MultipartFile file);

}
