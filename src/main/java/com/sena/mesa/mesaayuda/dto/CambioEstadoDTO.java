package com.sena.mesa.mesaayuda.dto;

import com.sena.mesa.mesaayuda.enums.Estado;
import jakarta.validation.constraints.NotNull;

public record CambioEstadoDTO(
        @NotNull
        Estado nuevoEstado
) {
}
