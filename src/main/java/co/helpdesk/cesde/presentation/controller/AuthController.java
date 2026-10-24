package co.helpdesk.cesde.presentation.controller;

import co.helpdesk.cesde.application.dto.auth.AuthResponse;
import co.helpdesk.cesde.application.dto.auth.LoginRequest;
import co.helpdesk.cesde.application.dto.auth.RegisterRequest;
import co.helpdesk.cesde.application.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
      @Valid
      @RequestBody LoginRequest request) {

    return ResponseEntity.ok(
        authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<AuthResponse> register(
      @Valid
      @RequestBody RegisterRequest request) {
    return ResponseEntity.ok(authService.register(request));
  }

}
