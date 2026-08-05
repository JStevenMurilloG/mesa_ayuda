package com.sena.mesa.mesaayuda.services;

import com.sena.mesa.mesaayuda.dto.RegistroUserDTO;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UsuarioRepository userRepository;
    private final UsuarioRepository usuarioRepository;

    UserService(UsuarioRepository userRepository, UsuarioRepository usuarioRepository){
        this.userRepository = userRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUser(RegistroUserDTO registroUserDTO){

        if (usuarioRepository.findByEmail(registroUserDTO.email()) != null){
            return null;
        }
        Usuario nuevoUsuario = new Usuario();

        nuevoUsuario.setEmail(registroUserDTO.email());
        nuevoUsuario.setPassword(registroUserDTO.password());
        nuevoUsuario.setNombre(registroUserDTO.nombre());

        return usuarioRepository.save(nuevoUsuario);
    }

}
