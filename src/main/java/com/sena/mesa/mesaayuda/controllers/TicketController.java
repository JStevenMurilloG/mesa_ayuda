package com.sena.mesa.mesaayuda.controllers;

import com.sena.mesa.mesaayuda.dto.CambioEstadoDTO;
import com.sena.mesa.mesaayuda.dto.TicketDTO;
import com.sena.mesa.mesaayuda.entities.Ticket;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.enums.Estado;
import com.sena.mesa.mesaayuda.enums.Rol;
import com.sena.mesa.mesaayuda.services.TicketService;
import com.sena.mesa.mesaayuda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;

    TicketController(TicketService ticketService, UserService userService) {
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Ticket> crearTicket(@Valid @RequestBody TicketDTO ticketDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = userService.findByEmail(auth.getName());
        Ticket ticket = ticketService.crearTicket(ticketDTO, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticket);
    }

    @GetMapping("/mios")
    public ResponseEntity<List<Ticket>> misTickets() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = userService.findByEmail(auth.getName());
        return ResponseEntity.ok(ticketService.listarPorCreador(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> obtenerTicket(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = userService.findByEmail(auth.getName());

        Ticket ticket = ticketService.findById(id)
                .orElse(null);

        if (ticket == null) {
            return ResponseEntity.notFound().build();
        }

        boolean esDueño = ticket.getCreadoPor().getEmail().equals(usuario.getEmail());
        boolean esSoporteOAdmin = usuario.getRol() == Rol.SOPORTE || usuario.getRol() == Rol.ADMIN;

        if (!esDueño && !esSoporteOAdmin) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<Ticket>> listarTodos() {
        return ResponseEntity.ok(ticketService.listarTodos());
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<Ticket> cambiarEstado(@PathVariable Long id, @Valid @RequestBody CambioEstadoDTO dto) {
        Ticket ticket = ticketService.cambiarEstado(id, dto.nuevoEstado());
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/vencidos")
    public ResponseEntity<List<Ticket>> ticketsVencidos() {
        return ResponseEntity.ok(ticketService.listarVencidos());
    }
}
