package com.sena.mesa.mesaayuda.repositories;

import com.sena.mesa.mesaayuda.entities.Ticket;
import com.sena.mesa.mesaayuda.entities.Usuario;
import com.sena.mesa.mesaayuda.enums.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findByCreadoPor(Usuario usuario);

    List<Ticket> findBySlaVenceEnBeforeAndEstadoNot(LocalDateTime fecha, Estado estado);
}
