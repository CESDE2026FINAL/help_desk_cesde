package co.helpdesk.cesde.presentation.controller;

import co.helpdesk.cesde.application.dto.user.UserDeleteResponse;
import co.helpdesk.cesde.application.dto.user.UserResponse;
import co.helpdesk.cesde.application.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class UserController {

  private final UserService usuarioService;

  @GetMapping("/debug")
  public ResponseEntity<?> debug(
      Authentication authentication) {

    return ResponseEntity.ok(
        Map.of(
            "usuario",
            authentication.getName(),
            "authorities",
            authentication.getAuthorities()
        ));
  }

  @PatchMapping("/{id}/desactivar")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<UserResponse> desactivarUsuario(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        usuarioService.desactivarUsuario(id));
  }

  @PatchMapping("/{id}/activar")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<UserResponse> activarUsuario(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        usuarioService.activarUsuario(id));
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<UserDeleteResponse> eliminarUsuario(
      @PathVariable Long id) {

    return ResponseEntity.ok(
        usuarioService.eliminarUsuario(id));
  }
}