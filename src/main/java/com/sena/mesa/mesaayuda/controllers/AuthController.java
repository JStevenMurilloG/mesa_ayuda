package com.sena.mesa.mesaayuda.controllers;

import com.sena.mesa.mesaayuda.dto.RegistroUserDTO;
import com.sena.mesa.mesaayuda.exceptions.EmailExisting;
import com.sena.mesa.mesaayuda.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService usuarioService;


    AuthController(UserService usuarioService){
        this.usuarioService = usuarioService;
    }
@PostMapping()
    public ResponseEntity registrarUsuario(@Valid @RequestBody RegistroUserDTO registroDto){
        if(usuarioService.registrarUser(registroDto) == null){
            throw new EmailExisting("Email ya existe");
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
