package com.sena.mesa.mesaayuda.repositories;

import com.sena.mesa.mesaayuda.entities.RefreshToken;
import com.sena.mesa.mesaayuda.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUsuario(Usuario usuario);
}
