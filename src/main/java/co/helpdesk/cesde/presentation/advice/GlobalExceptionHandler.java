package co.helpdesk.cesde.presentation.advice;

import java.time.OffsetDateTime;

import co.helpdesk.cesde.domain.exception.ApiError;
import co.helpdesk.cesde.domain.exception.CategoriaDuplicadaException;
import co.helpdesk.cesde.domain.exception.EmailDuplicadoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(EmailDuplicadoException.class)
  public ResponseEntity<ApiError> handleEmailDuplicado(
      EmailDuplicadoException ex) {

    ApiError error = new ApiError(
        HttpStatus.CONFLICT.value(),
        "EMAIL_DUPLICADO",
        ex.getMessage(),
        OffsetDateTime.now()
    );

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(error);
  }

  @ExceptionHandler(CategoriaDuplicadaException.class)
  public ResponseEntity<ApiError>
  handleCategoriaDuplicada(
      CategoriaDuplicadaException ex) {

    ApiError error = new ApiError(
        409,
        "CATEGORIA_DUPLICADA",
        ex.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(error);
  }
}