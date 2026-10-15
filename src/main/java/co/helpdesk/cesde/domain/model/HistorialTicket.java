package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

public class HistorialTicket {

  private Long id;

  private Ticket ticket;

  private Usuario usuario;

  private String campoModificado;

  private String valorAnterior;

  private String valorNuevo;

  private OffsetDateTime fechaCambio;

  public HistorialTicket() {
  }

  public HistorialTicket(
      Ticket ticket,
      Usuario usuario,
      String campoModificado,
      String valorAnterior,
      String valorNuevo) {

    this.ticket = ticket;
    this.usuario = usuario;
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

  public Usuario getUsuario() {
    return usuario;
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

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
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