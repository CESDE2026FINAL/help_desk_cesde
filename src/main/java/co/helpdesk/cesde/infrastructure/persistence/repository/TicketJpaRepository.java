package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.domain.enums.TicketStatus;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {

  List<TicketEntity> findBySolicitanteId(Long solicitanteId);

  List<TicketEntity> findByAgente(UserEntity agente);

  List<TicketEntity> findByEstado(TicketStatus estado);

  @Query("""
    SELECT t
    FROM TicketEntity t
    JOIN FETCH t.categoria
    JOIN FETCH t.solicitante
    JOIN FETCH t.agente
    WHERE t.agente.email = :email
    ORDER BY t.fechaCreacion DESC
    """)
  List<TicketEntity> findTicketsAsignados(
      String email);
}

