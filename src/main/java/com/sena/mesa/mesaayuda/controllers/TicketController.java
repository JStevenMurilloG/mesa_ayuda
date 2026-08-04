package com.sena.mesa.mesaayuda.controllers;

import com.sena.mesa.mesaayuda.dto.TicketDTO;
import com.sena.mesa.mesaayuda.entities.Ticket;
import com.sena.mesa.mesaayuda.services.TicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private TicketService ticketService;

    TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping()
    public Ticket createTicket( @RequestBody TicketDTO ticketDTO) {
       return ticketService.crearTicket(ticketDTO);
    }
}
