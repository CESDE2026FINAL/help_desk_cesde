package co.helpdesk.cesde.presentation.advice;

import java.time.OffsetDateTime;

import co.helpdesk.cesde.domain.exception.ApiError;
import co.helpdesk.cesde.domain.exception.DuplicateCategoryException;
import co.helpdesk.cesde.domain.exception.DuplicateEmailException;
import co.helpdesk.cesde.domain.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(DuplicateEmailException.class)
  public ResponseEntity<ApiError> handleEmailDuplicado(
      DuplicateEmailException ex) {

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

  @ExceptionHandler(DuplicateCategoryException.class)
  public ResponseEntity<ApiError>
  handleCategoriaDuplicada(
      DuplicateCategoryException ex) {

    ApiError error = new ApiError(
        409,
        "CATEGORIA_DUPLICADA",
        ex.getMessage(),
        OffsetDateTime.now());

    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(error);
  }

  @ExceptionHandler(
      UserNotFoundException.class)
  public ResponseEntity<ApiError>
  handleUsuarioNoEncontrado(
      UserNotFoundException ex) {

    ApiError error = new ApiError(
        HttpStatus.NOT_FOUND.value(),
        "USUARIO_NO_ENCONTRADO",
        ex.getMessage(),
        OffsetDateTime.now()
    );

    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(error);
  }
}