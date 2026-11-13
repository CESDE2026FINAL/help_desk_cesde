package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

import co.helpdesk.cesde.domain.enums.TicketStatus;
import co.helpdesk.cesde.domain.enums.Priority;

public class Ticket {

  private Long id;
  private String codigo;
  private String titulo;
  private String descripcion;

  private Priority prioridad;
  private TicketStatus estado;

  private Category category;

  private User solicitante;
  private User agente;

  private OffsetDateTime fechaCreacion;
  private OffsetDateTime fechaActualizacion;
  private OffsetDateTime fechaCierre;

  public Ticket() {
  }

  public Ticket(
      String codigo,
      String titulo,
      String descripcion,
      Priority prioridad,
      Category category,
      User solicitante) {

    this.codigo = codigo;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.prioridad = prioridad;
    this.category = category;
    this.solicitante = solicitante;
    this.estado = TicketStatus.ABIERTO;
    this.fechaCreacion = OffsetDateTime.now();
  }

  public void asignarAgente(User agente) {
    this.agente = agente;
  }

  public void cerrar() {
    this.estado = TicketStatus.CERRADO;
    this.fechaCierre = OffsetDateTime.now();
  }

  public void actualizarEstado(TicketStatus estado) {
    this.estado = estado;
  }

  public void actualizarPrioridad(Priority priority) {
    this.prioridad = priority;
  }

  // getters y setters


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public Priority getPrioridad() {
    return prioridad;
  }

  public void setPrioridad(Priority priority) {
    this.prioridad = priority;
  }

  public TicketStatus getEstado() {
    return estado;
  }

  public void setEstado(TicketStatus estado) {
    this.estado = estado;
  }

  public Category getCategoria() {
    return category;
  }

  public void setCategoria(Category category) {
    this.category = category;
  }

  public User getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(User solicitante) {
    this.solicitante = solicitante;
  }

  public User getAgente() {
    return agente;
  }

  public void setAgente(User agente) {
    this.agente = agente;
  }

  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  public OffsetDateTime getFechaActualizacion() {
    return fechaActualizacion;
  }

  public void setFechaActualizacion(OffsetDateTime fechaActualizacion) {
    this.fechaActualizacion = fechaActualizacion;
  }

  public OffsetDateTime getFechaCierre() {
    return fechaCierre;
  }

  public void setFechaCierre(OffsetDateTime fechaCierre) {
    this.fechaCierre = fechaCierre;
  }

  @Override
  public String toString() {
    return "Ticket{" +
        "id=" + id +
        ", codigo='" + codigo + '\'' +
        ", titulo='" + titulo + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", prioridad=" + prioridad +
        ", estado=" + estado +
        ", categoria=" + category +
        ", solicitante=" + solicitante +
        ", agente=" + agente +
        ", fechaCreacion=" + fechaCreacion +
        ", fechaActualizacion=" + fechaActualizacion +
        ", fechaCierre=" + fechaCierre +
        '}';
  }
}