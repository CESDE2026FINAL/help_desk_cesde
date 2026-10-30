package co.helpdesk.cesde.presentation.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.helpdesk.cesde.application.dto.ticket.CrearTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;
import co.helpdesk.cesde.application.service.TicketService;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
@Validated
public class TicketController {

  private final TicketService ticketService;

  @PostMapping
  @PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
  public ResponseEntity<TicketResponse> crearTicket(

      @RequestBody
      CrearTicketRequest request,

      Authentication authentication) {

    String email =
        authentication.getName();

    TicketResponse response =
        ticketService.crearTicket(
            request,
            email);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(response);
  }

  @GetMapping("/asignados")
  @PreAuthorize("hasAnyAuthority('SOPORTE','ADMIN')")
  public ResponseEntity<?> obtenerAsignados() {

    return ResponseEntity.ok().build();
  }

}