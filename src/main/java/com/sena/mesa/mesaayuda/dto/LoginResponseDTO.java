package com.sena.mesa.mesaayuda.dto;

public record LoginResponseDTO(
        String accessToken,
        String refreshToken
) {
}
