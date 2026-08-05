package com.sena.mesa.mesaayuda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegistroUserDTO(

        @NotNull
        @NotBlank
        String nombre,

        @Email
        @NotNull
        @NotBlank
        String email,

        @Min(6)
        String password


) {
}
