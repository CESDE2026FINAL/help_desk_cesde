package co.helpdesk.cesde.application.service.impl;

import java.time.OffsetDateTime;
import java.util.UUID;

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

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

  private final TicketJpaRepository ticketRepository;

  private final UserJpaRepository usuarioRepository;

  private final CategoryJpaRepository categoriaRepository;

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

    TicketEntity guardado =
        ticketRepository.save(ticket);

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

  private String generarCodigo() {

    return "TCK-"
        + UUID.randomUUID()
        .toString()
        .substring(0, 8)
        .toUpperCase();
  }
}