package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.ticket.CrearTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;

public interface TicketService {

  TicketResponse crearTicket (
      CrearTicketRequest request,
      String emailUsuario);

}
