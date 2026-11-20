package co.helpdesk.cesde.application.service.impl;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import co.helpdesk.cesde.application.service.HistorialTicketService;
import co.helpdesk.cesde.application.service.MailService;
import co.helpdesk.cesde.domain.exception.TicketNotFoundException;
import co.helpdesk.cesde.infrastructure.persistence.entity.NotificationEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.NotificationJpaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.helpdesk.cesde.application.dto.ticket.CreateTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;
import co.helpdesk.cesde.application.service.TicketService;
import co.helpdesk.cesde.domain.enums.TicketStatus;
import co.helpdesk.cesde.domain.exception.CategoryNotFoundException;
import co.helpdesk.cesde.domain.exception.UserNotFoundException;
import co.helpdesk.cesde.infrastructure.persistence.entity.CategoryEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UserEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.CategoryJpaRepository;
import co.helpdesk.cesde.infrastructure.persistence.repository.TicketJpaRepository;
import co.helpdesk.cesde.infrastructure.persistence.repository.UserJpaRepository;

import lombok.RequiredArgsConstructor;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

  private final MailService mailService;

  private final TicketJpaRepository ticketRepository;

  private final UserJpaRepository userRepository;

  private final HistorialTicketService historialTicketService;

  private final UserJpaRepository usuarioRepository;

  private final CategoryJpaRepository categoriaRepository;

  private final NotificationJpaRepository notificationRepository;

  @Override
  public TicketResponse crearTicket(
      CreateTicketRequest request,
      String emailSolicitante) {

    UserEntity usuario =
        usuarioRepository
            .findByEmail(emailSolicitante)
            .orElseThrow(() ->
                new UserNotFoundException(
                    emailSolicitante));

    CategoryEntity categoria =
        categoriaRepository
            .findById(request.categoriaId())
            .orElseThrow(() ->
                new CategoryNotFoundException(
                    request.categoriaId()));

    TicketEntity ticket = new TicketEntity();

    ticket.setCodigo(generarCodigo());

    ticket.setTitulo(request.titulo());

    ticket.setDescripcion(request.descripcion());

    ticket.setPrioridad(request.priority());

    ticket.setEstado(TicketStatus.ABIERTO);

    ticket.setCategoria(categoria);

    ticket.setSolicitante(usuario);

    ticket.setFechaCreacion(
        OffsetDateTime.now());

    ticket.setFechaActualizacion(
        OffsetDateTime.now());

    ticket.setAgente(categoria.getUsuarioSoporte());

    TicketEntity guardado =
        ticketRepository.save(ticket);

    historialTicketService
        .registrarCambio(
            guardado,
            usuario,
            "ESTADO",
            null,
            TicketStatus.ABIERTO.name());

    try {

      mailService.enviarNuevoTicket(
          guardado);

    } catch (Exception ex) {

      log.error(
          "Error enviando correo para ticket {}",
            guardado.getCodigo(),
          ex);
    }

    NotificationEntity notification =
        new NotificationEntity();

    notification.setUsuario(
        ticket.getAgente());

    notification.setTitulo(
        "Nuevo ticket asignado");

    notification.setMensaje(
        "Se le ha asignado el ticket "
            + ticket.getCodigo()
            + " - "
            + ticket.getTitulo());

    notification.setFechaCreacion(
        LocalDateTime.now());

    notificationRepository.save(
        notification);

    return new TicketResponse(
        guardado.getId(),
        guardado.getCodigo(),
        guardado.getTitulo(),
        guardado.getDescripcion(),
        guardado.getPrioridad(),
        guardado.getEstado(),
        guardado.getCategoria().getNombre(),
        guardado.getSolicitante().getNombreCompleto()
    );
  }

  @Override
  @Transactional(readOnly = true)
  public List<TicketResponse> obtenerTicketsAsignados(
      String email) {

    UserEntity soporte =
        usuarioRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(
                    "Usuario no encontrado"));

    return ticketRepository
        .findByAgente(soporte)
        .stream()
        .map(ticket -> new TicketResponse(
            ticket.getId(),
            ticket.getCodigo(),
            ticket.getTitulo(),
            ticket.getDescripcion(),
            ticket.getPrioridad(),
            ticket.getEstado(),
            ticket.getCategoria().getNombre(),
            ticket.getSolicitante().getNombreCompleto()))
        .toList();
  }

  @Override
  @Transactional
  public TicketResponse iniciarTicket(
      Long ticketId,
      String email) {

    TicketEntity ticket =
        ticketRepository
            .findById(ticketId)
            .orElseThrow(() ->
                new TicketNotFoundException(
                    "Ticket no encontrado"));

    UserEntity usuario =
        userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(
                    "Usuario no encontrado"));

    validarResponsable(ticket, usuario);

    TicketStatus estadoAnterior =
        ticket.getEstado();

    ticket.setEstado(
        TicketStatus.EN_PROCESO);

    ticket.setFechaActualizacion(
        OffsetDateTime.now());

    ticketRepository.save(ticket);

    historialTicketService
        .registrarCambio(
            ticket,
            usuario,
            "ESTADO",
            estadoAnterior.name(),
            TicketStatus.EN_PROCESO.name());

    return toResponse(ticket);
  }

  @Override
  @Transactional
  public TicketResponse resolverTicket(
      Long ticketId,
      String email) {

    TicketEntity ticket =
        ticketRepository
            .findById(ticketId)
            .orElseThrow(() ->
                new TicketNotFoundException(
                    "Ticket no encontrado"));

    UserEntity usuario =
        userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(
                    "Usuario no encontrado"));

    validarResponsable(ticket, usuario);

    TicketStatus estadoAnterior =
        ticket.getEstado();

    ticket.setEstado(
        TicketStatus.RESUELTO);

    ticket.setFechaActualizacion(
        OffsetDateTime.now());

    ticket.setFechaCierre(
        OffsetDateTime.now());

    ticketRepository.save(ticket);

    historialTicketService
        .registrarCambio(
            ticket,
            usuario,
            "ESTADO",
            estadoAnterior.name(),
            TicketStatus.RESUELTO.name());

    return toResponse(ticket);
  }

  @Override
  @Transactional
  public TicketResponse cerrarTicket(
      Long ticketId,
      String email) {

    TicketEntity ticket =
        ticketRepository
            .findById(ticketId)
            .orElseThrow(() ->
                new TicketNotFoundException(
                    "Ticket no encontrado"));

    UserEntity usuario =
        userRepository
            .findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(
                    "Usuario no encontrado"));

    TicketStatus estadoAnterior =
        ticket.getEstado();

    ticket.setEstado(
        TicketStatus.CERRADO);

    ticket.setFechaActualizacion(
        OffsetDateTime.now());

    ticketRepository.save(ticket);

    historialTicketService
        .registrarCambio(
            ticket,
            usuario,
            "ESTADO",
            estadoAnterior.name(),
            TicketStatus.CERRADO.name());

    return toResponse(ticket);
  }

  private String generarCodigo() {

    return "TCK-"
        + UUID.randomUUID()
        .toString()
        .substring(0, 8)
        .toUpperCase();
  }

  private void validarResponsable(
      TicketEntity ticket,
      UserEntity usuario) {

    boolean admin =
        usuario.getRol()
            .getNombre()
            .equals("ROLE_ADMIN");

    boolean agenteAsignado =
        ticket.getAgente() != null
            && ticket.getAgente()
            .getId()
            .equals(usuario.getId());

    if (!admin && !agenteAsignado) {

      throw new AccessDeniedException(
          "No tiene permiso para modificar este ticket");
    }
  }

  private TicketResponse toResponse(
      TicketEntity ticket) {

    return new TicketResponse(
        ticket.getId(),
        ticket.getCodigo(),
        ticket.getTitulo(),
        ticket.getDescripcion(),
        ticket.getPrioridad(),
        ticket.getEstado(),
        ticket.getCategoria()
            .getNombre(),
        ticket.getSolicitante()
            .getNombreCompleto()
    );
  }
}