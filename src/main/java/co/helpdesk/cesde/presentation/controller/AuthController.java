package co.helpdesk.cesde.presentation.controller;

import co.helpdesk.cesde.application.dto.AuthResponse;
import co.helpdesk.cesde.application.dto.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(
      @RequestBody LoginRequest request) {

    return ResponseEntity.ok(
        new AuthResponse("TOKEN_TEMPORAL"));
  }
}
