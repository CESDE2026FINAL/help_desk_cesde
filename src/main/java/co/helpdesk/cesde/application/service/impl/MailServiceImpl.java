package co.helpdesk.cesde.application.service.impl;

import co.helpdesk.cesde.application.service.MailService;
import co.helpdesk.cesde.infrastructure.persistence.entity.TicketEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

  private final JavaMailSender mailSender;

  @Override
  public void enviarNuevoTicket(TicketEntity ticket) {

    SimpleMailMessage mail = new SimpleMailMessage();

    mail.setTo(ticket.getAgente().getEmail());

    mail.setSubject("Nuevo Ticket: " + ticket.getCodigo());

    mail.setText("""
                Se le ha asignado un nuevo ticket.

                Código: %s

                Título: %s

                Categoría: %s

                Prioridad: %s

                Solicitante: %s

                Descripción:
                %s
                """
                      .formatted(
                          ticket.getCodigo(),
                          ticket.getTitulo(),
                          ticket.getCategoria().getNombre(),
                          ticket.getPrioridad(),
                          ticket.getSolicitante().getNombreCompleto(),
                          ticket.getDescripcion()
                      ));

    mailSender.send(mail);
  }
}
