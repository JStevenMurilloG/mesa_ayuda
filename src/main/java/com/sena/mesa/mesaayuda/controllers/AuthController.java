package com.sena.mesa.mesaayuda.controllers;

import com.sena.mesa.mesaayuda.dto.LoginRequestDTO;
import com.sena.mesa.mesaayuda.dto.LoginResponseDTO;
import com.sena.mesa.mesaayuda.dto.RegistroUserDTO;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.services.RefreshTokenService;
import com.sena.mesa.mesaayuda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    AuthController(UserService userService, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/registro")
    public ResponseEntity<Void> registrarUsuario(@Valid @RequestBody RegistroUserDTO registroDto) {
        userService.registrarUser(registroDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario usuario = userService.findByEmail(loginDto.email());

        LoginResponseDTO response = refreshTokenService.generateTokens(usuario);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> body) {
        String refreshToken = body.get("refreshToken");
        Map<String, String> newTokens = refreshTokenService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(newTokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Usuario usuario = userService.findByEmail(authentication.getName());
            if (usuario != null) {
                refreshTokenService.revocarTokens(usuario);
            }
        }
        return ResponseEntity.noContent().build();
    }
}
