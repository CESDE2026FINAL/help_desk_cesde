package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

import co.helpdesk.cesde.domain.enums.EstadoTicket;
import co.helpdesk.cesde.domain.enums.Prioridad;

public class Ticket {

  private Long id;
  private String codigo;
  private String titulo;
  private String descripcion;

  private Prioridad prioridad;
  private EstadoTicket estado;

  private Categoria categoria;

  private Usuario solicitante;
  private Usuario agente;

  private OffsetDateTime fechaCreacion;
  private OffsetDateTime fechaActualizacion;
  private OffsetDateTime fechaCierre;

  public Ticket() {
  }

  public Ticket(
      String codigo,
      String titulo,
      String descripcion,
      Prioridad prioridad,
      Categoria categoria,
      Usuario solicitante) {

    this.codigo = codigo;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.prioridad = prioridad;
    this.categoria = categoria;
    this.solicitante = solicitante;
    this.estado = EstadoTicket.ABIERTO;
    this.fechaCreacion = OffsetDateTime.now();
  }

  public void asignarAgente(Usuario agente) {
    this.agente = agente;
  }

  public void cerrar() {
    this.estado = EstadoTicket.CERRADO;
    this.fechaCierre = OffsetDateTime.now();
  }

  public void actualizarEstado(EstadoTicket estado) {
    this.estado = estado;
  }

  public void actualizarPrioridad(Prioridad prioridad) {
    this.prioridad = prioridad;
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

  public Prioridad getPrioridad() {
    return prioridad;
  }

  public void setPrioridad(Prioridad prioridad) {
    this.prioridad = prioridad;
  }

  public EstadoTicket getEstado() {
    return estado;
  }

  public void setEstado(EstadoTicket estado) {
    this.estado = estado;
  }

  public Categoria getCategoria() {
    return categoria;
  }

  public void setCategoria(Categoria categoria) {
    this.categoria = categoria;
  }

  public Usuario getSolicitante() {
    return solicitante;
  }

  public void setSolicitante(Usuario solicitante) {
    this.solicitante = solicitante;
  }

  public Usuario getAgente() {
    return agente;
  }

  public void setAgente(Usuario agente) {
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
        ", categoria=" + categoria +
        ", solicitante=" + solicitante +
        ", agente=" + agente +
        ", fechaCreacion=" + fechaCreacion +
        ", fechaActualizacion=" + fechaActualizacion +
        ", fechaCierre=" + fechaCierre +
        '}';
  }
}