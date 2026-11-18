package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.dto.notification.NotificationCounterResponse;
import co.helpdesk.cesde.application.dto.notification.NotificationResponse;
import co.helpdesk.cesde.application.service.NotificationService;
import co.helpdesk.cesde.domain.exception.NotificationNotFoundException;
import co.helpdesk.cesde.infrastructure.persistence.entity.NotificationEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.NotificationJpaRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationServiceImpl implements NotificationService {

  private final NotificationJpaRepository
      notificationRepository;

  @Override
  public List<NotificationResponse>
  obtenerMisNotificaciones(
      String email) {

    return notificationRepository
        .findByUsuarioEmailOrderByFechaCreacionDesc(
            email)
        .stream()
        .map(notification ->
            new NotificationResponse(
                notification.getId(),
                notification.getTitulo(),
                notification.getMensaje(),
                notification.getLeida(),
                notification.getFechaCreacion()))
        .toList();
  }

  @Override
  @Transactional
  public void marcarComoLeida(
      Long notificationId,
      String email) {

    NotificationEntity notification =
        notificationRepository
            .findById(notificationId)
            .orElseThrow(() ->
                new NotificationNotFoundException(
                    "Notificación no encontrada"));

    if (!notification
        .getUsuario()
        .getEmail()
        .equals(email)) {

      throw new AccessDeniedException(
          "No tiene permisos para modificar esta notificación");
    }

    notification.setLeida(true);

    notificationRepository.save(notification);
  }

  @Override
  @Transactional(readOnly = true)
  public NotificationCounterResponse
  obtenerCantidadNoLeidas(
      String email) {

    long cantidad =
        notificationRepository
            .countByUsuario_EmailAndLeidaFalse(
                email);

    return new NotificationCounterResponse(
        cantidad);
  }
}