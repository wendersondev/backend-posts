package br.com.oauth.backend.backendteste.infrastructure.repository;

import br.com.oauth.backend.backendteste.infrastructure.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmail(String email);

}
