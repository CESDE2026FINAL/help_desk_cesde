package co.helpdesk.cesde.infrastructure.persistence.repository;

import co.helpdesk.cesde.domain.enums.EstadoTicket;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketJpaRepository extends JpaRepository<TicketEntity, Long> {

  List<TicketEntity> findBySolicitanteId(Long solicitanteId);

  List<TicketEntity> findByAgenteId(Long agenteId);

  List<TicketEntity> findByEstado(EstadoTicket estado);
}

