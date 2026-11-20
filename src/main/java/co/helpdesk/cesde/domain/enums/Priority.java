package co.helpdesk.cesde.domain.enums;

public enum Priority {

  BAJA("Baja"),
  MEDIA("Media"),
  ALTA("Alta"),
  CRITICA("Crítica");

  private final String descripcion;

  Priority(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }
}