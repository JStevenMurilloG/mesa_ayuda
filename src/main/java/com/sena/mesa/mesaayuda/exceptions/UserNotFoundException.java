package com.sena.mesa.mesaayuda.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String mensaje) {
        super(mensaje);
    }


}
