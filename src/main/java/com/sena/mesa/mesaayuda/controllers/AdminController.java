package com.sena.mesa.mesaayuda.controllers;

import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.enums.Rol;
import com.sena.mesa.mesaayuda.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;

    AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/soporte")
    public ResponseEntity<Void> ascenderASoporte(@RequestParam Long usuarioId) {
        Usuario usuario = userService.findById(usuarioId);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        usuario.setRol(Rol.SOPORTE);
        userService.save(usuario);
        return ResponseEntity.ok().build();
    }
}
