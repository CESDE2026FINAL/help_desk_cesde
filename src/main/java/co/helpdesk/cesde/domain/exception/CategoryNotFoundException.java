package co.helpdesk.cesde.domain.exception;

public class CategoryNotFoundException extends RuntimeException {
  public CategoryNotFoundException(Long id) {
    super("No existe la categoría con id " + id);
  }
}
