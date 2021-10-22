package br.com.oauth.backend.backendteste.domain.business.service;

import br.com.oauth.backend.backendteste.api.rest.v1.request.UserRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.UserResponse;

public interface UserService {

    UserResponse saveUser(UserRequest request);

}
