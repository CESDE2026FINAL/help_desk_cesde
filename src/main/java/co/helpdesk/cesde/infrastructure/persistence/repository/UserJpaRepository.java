package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByEmail(String email);

  @Query("""
       SELECT u
       FROM UserEntity u
       JOIN FETCH u.rol
       WHERE u.email = :email
       AND u.activo = true
       """)
  Optional<UserEntity> findByEmailAndActivoTrue(@Param("email") String email);

  boolean existsByEmail(String email);

  List<UserEntity> findByActivoTrue();

  List<UserEntity> findByActivoFalse();
}
