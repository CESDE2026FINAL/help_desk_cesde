package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;

public interface MailService {
  void enviarNuevoTicket(TicketEntity ticket);
}
