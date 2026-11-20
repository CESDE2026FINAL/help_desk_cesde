package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;

public interface HistorialTicketService {

  void registrarCambio(
      TicketEntity ticket,
      UserEntity usuario,
      String campo,
      String valorAnterior,
      String valorNuevo);

}
