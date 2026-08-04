package com.sena.mesa.mesaayuda.dto;

import com.sena.mesa.mesaayuda.enums.Estado;
import com.sena.mesa.mesaayuda.enums.Prioridad;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TicketDTO(

        @NotNull
        @NotBlank
        String titulo,

        @NotNull
        @NotBlank
        String descripcion,

        @NotNull
        Prioridad prioridad,

        @NotNull
        Estado estado,

        @NotNull
        Long usuarioId

) {
}
