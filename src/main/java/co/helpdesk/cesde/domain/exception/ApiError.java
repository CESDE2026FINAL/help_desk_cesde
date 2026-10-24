package co.helpdesk.cesde.domain.exception;

import java.time.OffsetDateTime;

public record ApiError(
    int status,
    String error,
    String message,
    OffsetDateTime timestamp
) {
}
