package br.com.oauth.backend.backendteste.domain.business.service.impl;

import br.com.oauth.backend.backendteste.api.rest.v1.request.UserRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.UserResponse;
import br.com.oauth.backend.backendteste.domain.business.service.UserService;
import br.com.oauth.backend.backendteste.infrastructure.entity.RoleEntity;
import br.com.oauth.backend.backendteste.infrastructure.entity.UserEntity;
import br.com.oauth.backend.backendteste.infrastructure.repository.RoleRepository;
import br.com.oauth.backend.backendteste.infrastructure.repository.UserRepository;
import br.com.oauth.backend.backendteste.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepositoy;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserResponse saveUser(UserRequest request) {
        return Optional.of(request)
                .map(requestMapper -> {
                    RoleEntity role = RoleEntity.builder().name(Const.ROLE_ADMIN).build();
                    roleRepositoy.save(role);
                    UserEntity entity = new UserEntity(request.getName(), request.getEmail(), passwordEncoder.encode(request.getPassword()),
                            Arrays.asList(role));
                    UserEntity retorno = userRepository.save(entity);
                    return UserResponse
                            .builder()
                            .id(retorno.getId())
                            .email(retorno.getEmail())
                            .name(retorno.getName())
                            .build();
                }).get();
    }
}
