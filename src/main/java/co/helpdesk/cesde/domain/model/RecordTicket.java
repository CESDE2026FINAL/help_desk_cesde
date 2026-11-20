package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

public class RecordTicket {

  private Long id;

  private Ticket ticket;

  private User user;

  private String campoModificado;

  private String valorAnterior;

  private String valorNuevo;

  private OffsetDateTime fechaCambio;

  public RecordTicket() {
  }

  public RecordTicket(
      Ticket ticket,
      User user,
      String campoModificado,
      String valorAnterior,
      String valorNuevo) {

    this.ticket = ticket;
    this.user = user;
    this.campoModificado = campoModificado;
    this.valorAnterior = valorAnterior;
    this.valorNuevo = valorNuevo;
    this.fechaCambio = OffsetDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public User getUsuario() {
    return user;
  }

  public String getCampoModificado() {
    return campoModificado;
  }

  public String getValorAnterior() {
    return valorAnterior;
  }

  public String getValorNuevo() {
    return valorNuevo;
  }

  public OffsetDateTime getFechaCambio() {
    return fechaCambio;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public void setUsuario(User user) {
    this.user = user;
  }

  public void setCampoModificado(String campoModificado) {
    this.campoModificado = campoModificado;
  }

  public void setValorAnterior(String valorAnterior) {
    this.valorAnterior = valorAnterior;
  }

  public void setValorNuevo(String valorNuevo) {
    this.valorNuevo = valorNuevo;
  }

  public void setFechaCambio(OffsetDateTime fechaCambio) {
    this.fechaCambio = fechaCambio;
  }

  @Override
  public String toString() {
    return "HistorialTicket{" +
        "id=" + id +
        ", campoModificado='" + campoModificado + '\'' +
        ", valorAnterior='" + valorAnterior + '\'' +
        ", valorNuevo='" + valorNuevo + '\'' +
        ", fechaCambio=" + fechaCambio +
        '}';
  }
}