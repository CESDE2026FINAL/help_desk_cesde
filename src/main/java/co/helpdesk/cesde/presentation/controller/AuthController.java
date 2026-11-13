package co.helpdesk.cesde.presentation.controller;

import co.helpdesk.cesde.application.dto.auth.AuthResponse;
import co.helpdesk.cesde.application.dto.auth.LoginRequest;
import co.helpdesk.cesde.application.dto.auth.RegisterRequest;
import co.helpdesk.cesde.application.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
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

  @GetMapping("/auth/me")
  public ResponseEntity<?> me(Authentication authentication) {

    return ResponseEntity.ok(
        Map.of(
            "usuario", authentication.getName(),
            "authorities", authentication.getAuthorities()
        )
    );
  }

}
