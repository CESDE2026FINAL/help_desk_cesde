package co.helpdesk.cesde.application.dto.notification;

import java.time.LocalDateTime;

public record NotificationResponse(
    Long id,

    String titulo,

    String mensaje,

    Boolean leida,

    LocalDateTime fechaCreacion
) {
}
