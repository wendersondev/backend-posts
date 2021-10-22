package br.com.oauth.backend.backendteste.api.rest.v1;

import br.com.oauth.backend.backendteste.api.rest.v1.request.UserRequest;
import br.com.oauth.backend.backendteste.api.rest.v1.response.UserResponse;
import br.com.oauth.backend.backendteste.domain.business.service.UserService;
import br.com.oauth.backend.backendteste.infrastructure.entity.UserEntity;
import br.com.oauth.backend.backendteste.utils.Const;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signin")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request){
        log.info("Salvando usuário com email {} ", request.getEmail());
        return new ResponseEntity<UserResponse>(userService.saveUser(request), HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    @Secured({Const.ROLE_ADMIN})
    public UserEntity user() {
        log.info("Buscando usuário");
        return (UserEntity) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
