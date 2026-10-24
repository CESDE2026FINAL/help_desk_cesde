package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.RolEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolJpaRepository extends JpaRepository<RolEntity, Long> {

  Optional<RolEntity> findByNombre(String nombre);
}
