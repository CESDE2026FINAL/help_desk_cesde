package co.helpdesk.cesde.domain.exception;

public class DuplicateCategoryException
    extends RuntimeException {

  public DuplicateCategoryException(
      String nombre) {

    super("La categoría ya existe: " + nombre);
  }
}