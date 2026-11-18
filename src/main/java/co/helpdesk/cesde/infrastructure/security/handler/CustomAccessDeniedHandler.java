package co.helpdesk.cesde.infrastructure.security.handler;

import java.io.IOException;
import java.time.OffsetDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAccessDeniedHandler
    implements AccessDeniedHandler {

  @Override
  public void handle(
      HttpServletRequest request,
      HttpServletResponse response,
      AccessDeniedException accessDeniedException)
      throws IOException, ServletException {

    response.setStatus(
        HttpStatus.FORBIDDEN.value());

    response.setContentType(
        "application/json");

    String json =
        """
        {
          "status": 403,
          "error": "ACCESS_DENIED",
          "message": "No tiene permisos para realizar esta operación",
          "timestamp": "%s"
        }
        """
            .formatted(OffsetDateTime.now());

    response.getWriter().write(json);
  }
}