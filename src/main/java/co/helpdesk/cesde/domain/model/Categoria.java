package co.helpdesk.cesde.domain.model;

public class Categoria {

  private Long id;
  private String nombre;
  private String descripcion;
  private Boolean activo;

  public Categoria() {
  }

  public Categoria(
      Long id,
      String nombre,
      String descripcion,
      Boolean activo) {

    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.activo = activo;
  }

  public Categoria(
      String nombre,
      String descripcion) {

    this.nombre = nombre;
    this.descripcion = descripcion;
    this.activo = true;
  }

  public void activar() {
    this.activo = true;
  }

  public void desactivar() {
    this.activo = false;
  }

  public boolean estaActiva() {
    return Boolean.TRUE.equals(activo);
  }

  public Long getId() {
    return id;
  }

  public String getNombre() {
    return nombre;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public Boolean getActivo() {
    return activo;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setActivo(Boolean activo) {
    this.activo = activo;
  }

  @Override
  public String toString() {
    return "Categoria{" +
        "id=" + id +
        ", nombre='" + nombre + '\'' +
        ", descripcion='" + descripcion + '\'' +
        ", activo=" + activo +
        '}';
  }
}