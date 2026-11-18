package co.helpdesk.cesde.application.dto.ticket;

import java.time.LocalDateTime;

public record HistorialTicketResponse(
    String campo,

    String valorAnterior,

    String valorNuevo,

    String usuario,

    LocalDateTime fecha
) {
}
