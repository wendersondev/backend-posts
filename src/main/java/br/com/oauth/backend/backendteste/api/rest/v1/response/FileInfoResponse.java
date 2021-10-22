package br.com.oauth.backend.backendteste.api.rest.v1.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FileInfoResponse {

    private String name;
    private String url;

}
