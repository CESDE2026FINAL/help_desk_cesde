package co.helpdesk.cesde.domain.enums;

public enum TicketStatus {

  ABIERTO("Abierto"),
  EN_PROCESO("En proceso"),
  RESUELTO("Resuelto"),
  CERRADO("Cerrado"),
  CANCELADO("Cancelado");

  private final String descripcion;

  TicketStatus(String descripcion) {
    this.descripcion = descripcion;
  }

  public String getDescripcion() {
    return descripcion;
  }
}