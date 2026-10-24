package co.helpdesk.cesde.domain.exception;

public class UsuarioNoEncontradoException extends RuntimeException {
  public UsuarioNoEncontradoException(String email) {
    super("No existe el usuario: " + email);
  }
}
