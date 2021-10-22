package br.com.oauth.backend.backendteste.api.rest.v1.request;

import lombok.Getter;

@Getter
public class UserRequest {

    private String name;
    private String email;
    private String password;

}
