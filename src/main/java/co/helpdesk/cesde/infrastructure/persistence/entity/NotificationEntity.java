package co.helpdesk.cesde.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "notificaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "usuario_id",
      nullable = false)
  private UserEntity usuario;

  @Column(nullable = false)
  private String titulo;

  @Column(
      nullable = false,
      columnDefinition = "TEXT")
  private String mensaje;

  @Column(nullable = false)
  private Boolean leida = false;

  private LocalDateTime fechaCreacion;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public UserEntity getUsuario() {
    return usuario;
  }

  public void setUsuario(UserEntity usuario) {
    this.usuario = usuario;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }

  public Boolean getLeida() {
    return leida;
  }

  public void setLeida(Boolean leida) {
    this.leida = leida;
  }

  public LocalDateTime getFechaCreacion() {
    return fechaCreacion;
  }

  public void setFechaCreacion(LocalDateTime fechaCreacion) {
    this.fechaCreacion = fechaCreacion;
  }
}