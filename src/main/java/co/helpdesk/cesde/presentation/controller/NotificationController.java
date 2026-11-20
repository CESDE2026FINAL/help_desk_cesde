package co.helpdesk.cesde.presentation.controller;

import co.helpdesk.cesde.application.dto.notification.NotificationCounterResponse;
import co.helpdesk.cesde.application.dto.notification.NotificationResponse;
import co.helpdesk.cesde.application.service.NotificationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificaciones")
@RequiredArgsConstructor
public class NotificationController {

  private final NotificationService
      notificationService;

  @GetMapping
  @PreAuthorize(
      "hasAnyRole('USUARIO','SOPORTE','ADMIN')")
  public ResponseEntity<List<NotificationResponse>>
  obtenerMisNotificaciones(
      Authentication authentication) {

    String email =
        authentication.getName();

    return ResponseEntity.ok(
        notificationService
            .obtenerMisNotificaciones(
                email));
  }

  @PatchMapping("/{id}/leer")
  @PreAuthorize(
      "hasAnyRole('USUARIO','SOPORTE','ADMIN')")
  public ResponseEntity<Void>
  marcarComoLeida(
      @PathVariable Long id,
      Authentication authentication) {

    notificationService
        .marcarComoLeida(
            id,
            authentication.getName());

    return ResponseEntity.noContent()
        .build();
  }

  @GetMapping("/no-leidas")
  @PreAuthorize(
      "hasAnyRole('USUARIO','SOPORTE','ADMIN')")
  public ResponseEntity<NotificationCounterResponse>
  obtenerNoLeidas(
      Authentication authentication) {

    String email =
        authentication.getName();

    return ResponseEntity.ok(
        notificationService
            .obtenerCantidadNoLeidas(
                email));
  }
}