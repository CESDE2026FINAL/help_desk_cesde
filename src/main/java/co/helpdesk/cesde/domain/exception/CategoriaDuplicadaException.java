package co.helpdesk.cesde.domain.exception;

public class CategoriaDuplicadaException
    extends RuntimeException {

  public CategoriaDuplicadaException(
      String nombre) {

    super("La categoría ya existe: " + nombre);
  }
}