package com.sena.mesa.mesaayuda.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO(
        @Email
        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String password
) {
}
