package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationJpaRepository extends JpaRepository<NotificationEntity, Long> {
  List<NotificationEntity> findByUsuarioEmailOrderByFechaCreacionDesc(String email);
  long countByUsuario_EmailAndLeidaFalse(
      String email);
}
