package com.sena.mesa.mesaayuda.services;

import com.sena.mesa.mesaayuda.dto.LoginResponseDTO;
import com.sena.mesa.mesaayuda.entities.RefreshToken;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.repositories.RefreshTokenRepository;
import com.sena.mesa.mesaayuda.security.JwtUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    RefreshTokenService(RefreshTokenRepository refreshTokenRepository, JwtUtil jwtUtil) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponseDTO generateTokens(Usuario usuario) {
        String accessToken = jwtUtil.generateAccessToken(usuario.getEmail(), usuario.getRol().name());
        String refreshTokenValue = jwtUtil.generateRefreshToken(usuario.getEmail());

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(refreshTokenValue);
        refreshToken.setUsuario(usuario);
        refreshToken.setExpiraEn(LocalDateTime.now().plusDays(7));
        refreshToken.setRevocado(false);
        refreshTokenRepository.save(refreshToken);

        return new LoginResponseDTO(accessToken, refreshTokenValue);
    }

    public Map<String, String> refreshAccessToken(String refreshTokenValue) {
        if (!jwtUtil.validateToken(refreshTokenValue)) {
            throw new RuntimeException("Refresh token inválido o expirado");
        }

        RefreshToken refreshToken = refreshTokenRepository.findByToken(refreshTokenValue)
                .orElseThrow(() -> new RuntimeException("Refresh token no encontrado"));

        if (refreshToken.isRevocado()) {
            throw new RuntimeException("Refresh token revocado");
        }

        if (refreshToken.getExpiraEn().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token expirado");
        }

        Usuario usuario = refreshToken.getUsuario();
        String newAccessToken = jwtUtil.generateAccessToken(usuario.getEmail(), usuario.getRol().name());

        return Map.of("accessToken", newAccessToken);
    }

    public void revocarTokens(Usuario usuario) {
        refreshTokenRepository.deleteByUsuario(usuario);
    }
}
