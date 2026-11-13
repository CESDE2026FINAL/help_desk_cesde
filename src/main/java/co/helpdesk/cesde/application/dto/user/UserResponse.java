package co.helpdesk.cesde.application.dto.user;

public record UserResponse(
    Long id,

    String nombreCompleto,

    String email,

    String rol,

    Boolean activo
) {
}
