package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.ticket.CreateTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;

import java.util.List;

public interface TicketService {

  TicketResponse crearTicket (
      CreateTicketRequest request,
      String emailUsuario);

  List<TicketResponse> obtenerTicketsAsignados(
      String email);

  TicketResponse iniciarTicket(
      Long ticketId,
      String email);

  TicketResponse resolverTicket(
      Long ticketId,
      String email);

  TicketResponse cerrarTicket(
      Long ticketId,
      String email);

}
