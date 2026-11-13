package co.helpdesk.cesde.domain.exception;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String email) {
    super("No existe el usuario: " + email);
  }

  public UserNotFoundException(Long id) {
    super("No existe un usuario con id: " + id);
  }
}
