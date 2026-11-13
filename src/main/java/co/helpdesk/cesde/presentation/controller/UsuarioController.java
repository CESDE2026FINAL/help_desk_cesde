package co.helpdesk.cesde.presentation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import co.helpdesk.cesde.application.dto.user.UsuarioDeleteResponse;
import co.helpdesk.cesde.application.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

  private final UsuarioService usuarioService;

  @DeleteMapping("/{id}")
  @PreAuthorize("hasAuthority('ROLE_ADMIN')")
  public ResponseEntity<UsuarioDeleteResponse> eliminarUsuario(
      @PathVariable Long id) {
    return ResponseEntity.ok(
        usuarioService.eliminarUsuario(id));
  }

}