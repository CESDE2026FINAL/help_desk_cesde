package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.infrastructure.persistence.entity.HistorialTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistorialTicketJpaRepository
    extends JpaRepository<
        HistorialTicketEntity,
        Long> {

  List<HistorialTicketEntity>
  findByTicketIdOrderByFechaCambioDesc(
      Long ticketId);
}