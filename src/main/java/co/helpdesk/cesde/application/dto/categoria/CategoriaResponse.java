package co.helpdesk.cesde.application.dto.categoria;

public record CategoriaResponse(

    Long id,

    String nombre,

    String descripcion,

    Boolean activo
) {
}