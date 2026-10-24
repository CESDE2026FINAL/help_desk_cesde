package co.helpdesk.cesde.application.dto.ticket;

import co.helpdesk.cesde.domain.enums.EstadoTicket;
import co.helpdesk.cesde.domain.enums.Prioridad;

public record TicketResponse(

    Long id,

    String codigo,

    String titulo,

    String descripcion,

    Prioridad prioridad,

    EstadoTicket estado,

    String categoria,

    String solicitante
) {
}
