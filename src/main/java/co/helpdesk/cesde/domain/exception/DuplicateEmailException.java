package co.helpdesk.cesde.domain.exception;

public class DuplicateEmailException extends RuntimeException {
  public DuplicateEmailException(String email) {
    super("Ya existe un usuario registrado con el correo: " + email);
  }
}
