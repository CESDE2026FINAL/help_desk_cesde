package co.helpdesk.cesde.application.service;

import co.helpdesk.cesde.application.dto.notification.NotificationCounterResponse;
import co.helpdesk.cesde.application.dto.notification.NotificationResponse;

import java.util.List;

public interface NotificationService {
  List<NotificationResponse> obtenerMisNotificaciones(String email);
  void marcarComoLeida(Long notificationId,
                       String email);
  NotificationCounterResponse
  obtenerCantidadNoLeidas(
      String email);
}
