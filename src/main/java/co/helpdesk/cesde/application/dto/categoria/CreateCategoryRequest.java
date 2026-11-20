package co.helpdesk.cesde.application.dto.categoria;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(

    @NotBlank
    String nombre,

    String descripcion
) {
}