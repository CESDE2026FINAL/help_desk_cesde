package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

public class Comentario {

  private Long id;

  private Ticket ticket;

  private Usuario autor;

  private String contenido;

  private Boolean esInterno;

  private OffsetDateTime fechaCreacion;

  public Comentario() {
  }

  public Comentario(
      Ticket ticket,
      Usuario autor,
      String contenido,
      Boolean esInterno) {

    this.ticket = ticket;
    this.autor = autor;
    this.contenido = contenido;
    this.esInterno = esInterno;
    this.fechaCreacion = OffsetDateTime.now();
  }

  public Long getId() {
    return id;
  }

  public Ticket getTicket() {
    return ticket;
  }

  public Usuario getAutor() {
    return autor;
  }

  public String getContenido() {
    return contenido;
  }

  public Boolean getEsInterno() {
    return esInterno;
  }

  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setTicket(Ticket ticket) {
    this.ticket = ticket;
  }

  public void setAutor(Usuario autor) {
    this.autor = autor;
  }

  public void setContenido(String contenido) {
    this.contenido = contenido;
  }

  public void setEsInterno(Boolean esInterno) {
    this.esInterno = esInterno;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Override
  public String toString() {
    return "Comentario{" +
        "id=" + id +
        ", autor=" + autor.getEmail() +
        ", esInterno=" + esInterno +
        ", fechaCreacion=" + fechaCreacion +
        '}';
  }
}