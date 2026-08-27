package com.sena.mesa.mesaayuda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegistroUserDTO(

        @NotNull
        @NotBlank
        String nombre,

        @Email
        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        @Size(min = 6)
        String password


) {
}
