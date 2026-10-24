package co.helpdesk.cesde.infrastructure.persistence.entity;

import co.helpdesk.cesde.domain.enums.EstadoTicket;
import co.helpdesk.cesde.domain.enums.Prioridad;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Table(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, unique = true, length = 20)
  private String codigo;

  @Column(nullable = false, length = 150)
  private String titulo;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String descripcion;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Prioridad prioridad;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private EstadoTicket estado;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "categoria_id",
      nullable = false)
  private CategoriaEntity categoria;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "usuario_id",
      nullable = false)
  private UsuarioEntity solicitante;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "agente_id")
  private UsuarioEntity agente;

  @Column(name = "fecha_creacion")
  private OffsetDateTime fechaCreacion;

  @Column(name = "fecha_actualizacion")
  private OffsetDateTime fechaActualizacion;

  @Column(name = "fecha_cierre")
  private OffsetDateTime fechaCierre;
}