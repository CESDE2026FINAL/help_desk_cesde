package co.helpdesk.cesde.domain.exception;

public class EmailDuplicadoException extends RuntimeException {
  public EmailDuplicadoException(String email) {
    super("Ya existe un usuario registrado con el correo: " + email);
  }
}
