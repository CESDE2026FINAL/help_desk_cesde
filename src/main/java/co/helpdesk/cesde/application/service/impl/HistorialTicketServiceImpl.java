package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.service.HistorialTicketService;
import co.helpdesk.cesde.infrastructure.persistence.entity.HistorialTicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.HistorialTicketJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class HistorialTicketServiceImpl
    implements HistorialTicketService {

  private final HistorialTicketJpaRepository repository;

  @Override
  @Transactional
  public void registrarCambio(
      TicketEntity ticket,
      UserEntity usuario,
      String campo,
      String valorAnterior,
      String valorNuevo) {

    HistorialTicketEntity historial =
        new HistorialTicketEntity();

    historial.setTicket(ticket);

    historial.setUsuario(usuario);

    historial.setCampoModificado(campo);

    historial.setValorAnterior(
        valorAnterior);

    historial.setValorNuevo(
        valorNuevo);

    historial.setFechaCambio(
        LocalDateTime.now());

    repository.save(historial);
  }
}
