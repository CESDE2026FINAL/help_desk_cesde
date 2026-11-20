package co.helpdesk.cesde.application.dto;

import java.time.LocalDateTime;

public record ErrorApiResponse(
    Integer status,
    String error,
    String message,
    LocalDateTime timestamp
) {
}
