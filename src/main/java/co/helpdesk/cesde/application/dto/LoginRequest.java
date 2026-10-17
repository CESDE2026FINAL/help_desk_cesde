package co.helpdesk.cesde.application.dto;

public record LoginRequest(
    String email,
    String password
) {
}
