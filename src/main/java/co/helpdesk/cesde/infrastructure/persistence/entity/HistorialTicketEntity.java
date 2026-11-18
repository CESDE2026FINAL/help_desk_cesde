package co.helpdesk.cesde.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "historial_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HistorialTicketEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "ticket_id",
      nullable = false)
  private TicketEntity ticket;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "usuario_id",
      nullable = false)
  private UserEntity usuario;

  @Column(
      name = "campo_modificado",
      nullable = false,
      length = 50)
  private String campoModificado;

  @Column(name = "valor_anterior")
  private String valorAnterior;

  @Column(name = "valor_nuevo")
  private String valorNuevo;

  @Column(name = "fecha_cambio")
  private LocalDateTime fechaCambio;
}
