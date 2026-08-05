package com.sena.mesa.mesaayuda.exceptions;

public class EmailExisting extends RuntimeException {

    public EmailExisting(String mensaje){
        super(mensaje);
    }
}
