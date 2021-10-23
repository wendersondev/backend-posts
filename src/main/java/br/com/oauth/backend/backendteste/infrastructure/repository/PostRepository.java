package br.com.oauth.backend.backendteste.infrastructure.repository;

import br.com.oauth.backend.backendteste.infrastructure.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByUserId(Long userId);

}
