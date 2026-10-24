package co.helpdesk.cesde.application.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CrearCategoriaRequest(

    @NotBlank
    String nombre,

    String descripcion
) {
}