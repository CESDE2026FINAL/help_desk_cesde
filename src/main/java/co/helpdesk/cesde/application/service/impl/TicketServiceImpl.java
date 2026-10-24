package co.helpdesk.cesde.application.service.impl;

import java.time.OffsetDateTime;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.helpdesk.cesde.application.dto.ticket.CrearTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;
import co.helpdesk.cesde.application.service.TicketService;
import co.helpdesk.cesde.domain.enums.EstadoTicket;
import co.helpdesk.cesde.domain.exception.CategoriaNoEncontradaException;
import co.helpdesk.cesde.domain.exception.UsuarioNoEncontradoException;
import co.helpdesk.cesde.infrastructure.persistence.entity.CategoriaEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import co.helpdesk.cesde.infrastructure.persistence.entity.UsuarioEntity;
import co.helpdesk.cesde.infrastructure.persistence.repository.CategoriaJpaRepository;
import co.helpdesk.cesde.infrastructure.persistence.repository.TicketJpaRepository;
import co.helpdesk.cesde.infrastructure.persistence.repository.UsuarioJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketServiceImpl implements TicketService {

  private final TicketJpaRepository ticketRepository;

  private final UsuarioJpaRepository usuarioRepository;

  private final CategoriaJpaRepository categoriaRepository;

  @Override
  public TicketResponse crearTicket(
      CrearTicketRequest request,
      String emailSolicitante) {

    UsuarioEntity usuario =
        usuarioRepository
            .findByEmail(emailSolicitante)
            .orElseThrow(() ->
                new UsuarioNoEncontradoException(
                    emailSolicitante));

    CategoriaEntity categoria =
        categoriaRepository
            .findById(request.categoriaId())
            .orElseThrow(() ->
                new CategoriaNoEncontradaException(
                    request.categoriaId()));

    TicketEntity ticket = new TicketEntity();

    ticket.setCodigo(generarCodigo());

    ticket.setTitulo(request.titulo());

    ticket.setDescripcion(request.descripcion());

    ticket.setPrioridad(request.prioridad());

    ticket.setEstado(EstadoTicket.ABIERTO);

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