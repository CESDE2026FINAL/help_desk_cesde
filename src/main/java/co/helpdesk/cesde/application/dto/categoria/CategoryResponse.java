package co.helpdesk.cesde.application.dto.categoria;

public record CategoryResponse(

    Long id,

    String nombre,

    String descripcion,

    Boolean activo
) {
}