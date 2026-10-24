package co.helpdesk.cesde.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.CategoriaEntity;

@Repository
public interface CategoriaJpaRepository
    extends JpaRepository<CategoriaEntity, Long> {

  Optional<CategoriaEntity> findByNombre(String nombre);

  boolean existsByNombre(String nombre);

}