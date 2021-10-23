package br.com.oauth.backend.backendteste.api.rest.v1.response;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostResponse {

    private Long id;
    private Boolean privatePost;
    private String obs;
    private String name;
    private String url;

}
