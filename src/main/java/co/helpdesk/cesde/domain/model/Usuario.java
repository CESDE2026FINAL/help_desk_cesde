package co.helpdesk.cesde.domain.model;

import java.time.OffsetDateTime;

public class Usuario {

  private Long id;
  private String nombreCompleto;
  private String email;
  private String password;
  private Rol rol;
  private Boolean activo;
  private OffsetDateTime fechaCreacion;

  public Usuario() {
  }

  public Usuario(
      Long id,
      String nombreCompleto,
      String email,
      String password,
      Rol rol,
      Boolean activo,
      OffsetDateTime fechaCreacion) {

    this.id = id;
    this.nombreCompleto = nombreCompleto;
    this.email = email;
    this.password = password;
    this.rol = rol;
    this.activo = activo;
    this.fechaCreacion = fechaCreacion;
  }

  public Usuario(
      String nombreCompleto,
      String email,
      String password,
      Rol rol) {

    this.nombreCompleto = nombreCompleto;
    this.email = email;
    this.password = password;
    this.rol = rol;
    this.activo = true;
    this.fechaCreacion = OffsetDateTime.now();
  }

  public void activar() {
    this.activo = true;
  }

  public void desactivar() {
    this.activo = false;
  }

  public boolean estaActivo() {
    return Boolean.TRUE.equals(activo);
  }

  public Long getId() {
    return id;
  }

  public String getNombreCompleto() {
    return nombreCompleto;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public Rol getRol() {
    return rol;
  }

  public Boolean getActivo() {
    return activo;
  }

  public OffsetDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombreCompleto(String nombreCompleto) {
    this.nombreCompleto = nombreCompleto;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setRol(Rol rol) {
    this.rol = rol;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
  }

  public void setFechaCreacion(OffsetDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }

  @Override
  public String toString() {
    return "Usuario{" +
        "id=" + id +
        ", nombreCompleto='" + nombreCompleto + '\'' +
        ", email='" + email + '\'' +
        ", rol=" + rol +
        ", activo=" + activo +
        ", fechaCreacion=" + fechaCreacion +
        '}';
  }
}