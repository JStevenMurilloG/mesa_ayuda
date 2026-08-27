package com.sena.mesa.mesaayuda.services;

import com.sena.mesa.mesaayuda.dto.TicketDTO;
import com.sena.mesa.mesaayuda.entities.Ticket;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.enums.Estado;
import com.sena.mesa.mesaayuda.enums.Prioridad;
import com.sena.mesa.mesaayuda.exceptions.TicketNotFoundException;
import com.sena.mesa.mesaayuda.repositories.TicketRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket crearTicket(TicketDTO ticketDTO, Usuario usuario) {
        Ticket nuevoTicket = new Ticket();

        nuevoTicket.setTitulo(ticketDTO.titulo());
        nuevoTicket.setDescripcion(ticketDTO.descripcion());
        nuevoTicket.setPrioridad(ticketDTO.prioridad());
        nuevoTicket.setEstado(Estado.ABIERTO);
        nuevoTicket.setCreadoEn(LocalDateTime.now());
        nuevoTicket.setCreadoPor(usuario);

        switch (ticketDTO.prioridad()) {
            case ALTA:
                nuevoTicket.setSlaVenceEn(LocalDateTime.now().plusHours(4));
                break;
            case MEDIA:
                nuevoTicket.setSlaVenceEn(LocalDateTime.now().plusHours(24));
                break;
            case BAJA:
                nuevoTicket.setSlaVenceEn(LocalDateTime.now().plusHours(72));
                break;
        }

        ticketRepository.save(nuevoTicket);
        return nuevoTicket;
    }

    public List<Ticket> listarTodos() {
        return ticketRepository.findAll();
    }

    public List<Ticket> listarPorCreador(Usuario usuario) {
        return ticketRepository.findByCreadoPor(usuario);
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket cambiarEstado(Long ticketId, Estado nuevoEstado) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket no encontrado"));
        ticket.setEstado(nuevoEstado);
        ticketRepository.save(ticket);
        return ticket;
    }

    public List<Ticket> listarVencidos() {
        return ticketRepository.findBySlaVenceEnBeforeAndEstadoNot(LocalDateTime.now(), Estado.RESUELTO);
    }
}
