package co.helpdesk.cesde.application.dto.ticket;

import co.helpdesk.cesde.domain.enums.Prioridad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CrearTicketRequest(
    @NotBlank
    String titulo,

    @NotBlank
    String descripcion,

    @NotNull
    Prioridad prioridad,

    @NotNull
    Long categoriaId
) {
}
