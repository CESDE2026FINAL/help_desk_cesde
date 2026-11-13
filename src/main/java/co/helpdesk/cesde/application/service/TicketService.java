package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.ticket.CreateTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;

public interface TicketService {

  TicketResponse crearTicket (
      CreateTicketRequest request,
      String emailUsuario);

}
