package co.helpdesk.cesde.application.dto.ticket;

import co.helpdesk.cesde.domain.enums.TicketStatus;
import co.helpdesk.cesde.domain.enums.Priority;

public record TicketResponse(

    Long id,

    String codigo,

    String titulo,

    String descripcion,

    Priority priority,

    TicketStatus estado,

    String categoria,

    String solicitante
) {
}
