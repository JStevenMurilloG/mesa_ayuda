package com.sena.mesa.mesaayuda.dto;

import com.sena.mesa.mesaayuda.enums.Prioridad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketDTO(

        @NotNull
        @NotBlank
        String titulo,

        @NotNull
        @NotBlank
        String descripcion,

        @NotNull
        Prioridad prioridad

) {
}
