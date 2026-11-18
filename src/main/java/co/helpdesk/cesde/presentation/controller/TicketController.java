package co.helpdesk.cesde.presentation.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import co.helpdesk.cesde.application.dto.ticket.CreateTicketRequest;
import co.helpdesk.cesde.application.dto.ticket.TicketResponse;
import co.helpdesk.cesde.application.service.TicketService;

import java.util.List;

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
      CreateTicketRequest request,
      Authentication authentication) {

    System.out.println("Creando Ticket:");

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

  @GetMapping("/asignados/total")
  @PreAuthorize("hasAnyRole('ADMIN')")
  public ResponseEntity<?> obtenerAsignadosTotal() {
    return ResponseEntity.ok().build();
  }

  @GetMapping("/asignados")
  @PreAuthorize("hasAnyRole('SOPORTE','ADMIN')")
  public ResponseEntity<List<TicketResponse>>
  obtenerAsignados(
      Authentication authentication) {

    String email =
        authentication.getName();

    return ResponseEntity.ok(
        ticketService
            .obtenerTicketsAsignados(
                email));
  }

  @GetMapping("/debug")
  public ResponseEntity<?> debug(Authentication authentication) {

    return ResponseEntity.ok(
        authentication.getAuthorities());
  }

  @PatchMapping("/{id}/iniciar")
  @PreAuthorize("hasAnyRole('SOPORTE','ADMIN')")
  public ResponseEntity<TicketResponse>
  iniciarTicket(
      @PathVariable Long id,
      Authentication authentication) {

    return ResponseEntity.ok(
        ticketService.iniciarTicket(
            id,
            authentication.getName()));
  }

  @PatchMapping("/{id}/resolver")
  @PreAuthorize("hasAnyRole('SOPORTE','ADMIN')")
  public ResponseEntity<TicketResponse>
  resolver(
      @PathVariable Long id,
      Authentication authentication) {

    return ResponseEntity.ok(
        ticketService.resolverTicket(
            id,
            authentication.getName()));
  }

  @PatchMapping("/{id}/cerrar")
  @PreAuthorize("hasAnyRole('USUARIO','ADMIN')")
  public ResponseEntity<TicketResponse>
  cerrar(
      @PathVariable Long id,
      Authentication authentication) {

    return ResponseEntity.ok(
        ticketService.cerrarTicket(
            id,
            authentication.getName()));
  }

}