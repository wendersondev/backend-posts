package br.com.oauth.backend.backendteste.api.rest.v1.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostRequest {

    private String obs;
    private Boolean privatePost;

}
