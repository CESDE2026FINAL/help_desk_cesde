package co.helpdesk.cesde.domain.model;

import java.time.LocalDate;
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

  private LocalDate fechaCreacion;
  private LocalDate fechaActualizacion;
  private LocalDate fechaCierre;

  public Ticket() {
  }

  public Ticket(
      Long id,
      String codigo,
      String titulo,
      String descripcion,
      Prioridad prioridad,
      EstadoTicket estado,
      Categoria categoria,
      Usuario solicitante,
      Usuario agente,
      LocalDate fechaCreacion,
      LocalDate fechaActualizacion,
      LocalDate fechaCierre) {

    this.id = id;
    this.codigo = codigo;
    this.titulo = titulo;
    this.descripcion = descripcion;
    this.prioridad = prioridad;
    this.estado = estado;
    this.categoria = categoria;
    this.solicitante = solicitante;
    this.agente = agente;
    this.fechaCreacion = fechaCreacion;
    this.fechaActualizacion = fechaActualizacion;
    this.fechaCierre = fechaCierre;
  }

  public Long getId() { return id; }

  public String getCodigo() { return codigo; }

  public String getTitulo() { return titulo; }

  public String getDescripcion() { return descripcion; }

  public Prioridad getPrioridad() { return prioridad; }

  public EstadoTicket getEstado() { return estado; }

  public Categoria getCategoria() { return categoria; }

  public Usuario getSolicitante() { return solicitante; }

  public Usuario getAgente() { return agente; }

  public LocalDate getFechaCreacion() { return fechaCreacion; }

  public LocalDate getFechaActualizacion() { return fechaActualizacion; }

  public LocalDate getFechaCierre() { return fechaCierre; }

  public void setId(Long id) { this.id = id; }

  public void setCodigo(String codigo) { this.codigo = codigo; }

  public void setTitulo(String titulo) { this.titulo = titulo; }

  public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

  public void setPrioridad(Prioridad prioridad) { this.prioridad = prioridad; }

  public void setEstado(EstadoTicket estado) { this.estado = estado; }

  public void setCategoria(Categoria categoria) { this.categoria = categoria; }

  public void setSolicitante(Usuario solicitante) { this.solicitante = solicitante; }

  public void setAgente(Usuario agente) { this.agente = agente; }

  public void setFechaCreacion(LocalDate fechaCreacion) { this.fechaCreacion = fechaCreacion; }

  public void setFechaActualizacion(LocalDate fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

  public void setFechaCierre(LocalDate fechaCierre) { this.fechaCierre = fechaCierre; }
}