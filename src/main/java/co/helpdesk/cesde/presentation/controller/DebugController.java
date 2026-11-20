package co.helpdesk.cesde.presentation.controller;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/debug")
public class DebugController {

  @GetMapping("/auth")
  public ResponseEntity<?> auth(Authentication authentication) {

    if (authentication == null) {
      return ResponseEntity.ok("AUTHENTICATION NULL");
    }

    return ResponseEntity.ok(Map.of(
        "name", authentication.getName(),
        "authenticated", authentication.isAuthenticated(),
        "authorities", authentication.getAuthorities()
    ));
  }

  @GetMapping("/headers")
  public ResponseEntity<?> headers(
      HttpServletRequest request) {

    return ResponseEntity.ok(
        request.getHeader("Authorization"));
  }

}