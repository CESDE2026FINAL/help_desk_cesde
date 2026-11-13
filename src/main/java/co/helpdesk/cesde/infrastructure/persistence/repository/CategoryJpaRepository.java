package co.helpdesk.cesde.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.CategoryEntity;

@Repository
public interface CategoryJpaRepository
    extends JpaRepository<CategoryEntity, Long> {

  Optional<CategoryEntity> findByNombre(String nombre);

  boolean existsByNombre(String nombre);

}