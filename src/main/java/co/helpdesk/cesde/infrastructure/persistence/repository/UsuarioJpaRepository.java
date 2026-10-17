package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.RolEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
  Optional<UsuarioEntity> findByEmail(String email);
}
