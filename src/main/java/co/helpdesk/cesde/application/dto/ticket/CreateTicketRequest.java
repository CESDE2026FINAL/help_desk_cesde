package co.helpdesk.cesde.application.dto.ticket;

import co.helpdesk.cesde.domain.enums.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTicketRequest(
    @NotBlank
    String titulo,

    @NotBlank
    String descripcion,

    @NotNull
    Priority priority,

    @NotNull
    Long categoriaId
) {
}
