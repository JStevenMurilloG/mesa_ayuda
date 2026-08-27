package com.sena.mesa.mesaayuda.exceptions;

public class TicketNotFoundException extends RuntimeException {

    public TicketNotFoundException(String mensaje) {
        super(mensaje);
    }
}
