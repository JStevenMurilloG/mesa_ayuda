package com.sena.mesa.mesaayuda.services;

import com.sena.mesa.mesaayuda.dto.TicketDTO;
import com.sena.mesa.mesaayuda.entities.Ticket;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.enums.Estado;
import com.sena.mesa.mesaayuda.enums.Prioridad;
import com.sena.mesa.mesaayuda.exceptions.UserNotFoundException;
import com.sena.mesa.mesaayuda.repositories.TicketRepository;
import com.sena.mesa.mesaayuda.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TicketService {

    private final UsuarioRepository usuarioRepository;
    private final TicketRepository ticketRepository;

    TicketService(UsuarioRepository usuarioRepository, TicketRepository ticketRepository) {
        this.usuarioRepository = usuarioRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket crearTicket(TicketDTO ticketDTO){

        Optional<Usuario> usuario = usuarioRepository.findById(ticketDTO.usuarioId());

        if(usuario.isEmpty()){
            throw new UserNotFoundException("Usuario no encontrado");
        }

        Usuario usuarioActual = usuario.get();

        Ticket nuevoTicket = new Ticket();

        nuevoTicket.setTitulo(ticketDTO.titulo());
        nuevoTicket.setDescripcion(ticketDTO.descripcion());
        nuevoTicket.setPrioridad(ticketDTO.prioridad());
        nuevoTicket.setEstado(Estado.ABIERTO);
        nuevoTicket.setCreadoEn(LocalDateTime.now());
        nuevoTicket.setCreadoPor(usuarioActual);

        switch (ticketDTO.prioridad()){
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
}
