package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

public class Comment {

  private Long id;

  private Ticket ticket;

  private User autor;

  private String contenido;

  private Boolean esInterno;

  private OffsetDateTime fechaCreacion;

  public Comment() {
  }

  public Comment(
      Ticket ticket,
      User autor,
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

  public User getAutor() {
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

  public void setAutor(User autor) {
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