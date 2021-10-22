package br.com.oauth.backend.backendteste.infrastructure.repository;

import br.com.oauth.backend.backendteste.infrastructure.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
}
