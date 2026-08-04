package com.sena.mesa.mesaayuda.entities;

import com.sena.mesa.mesaayuda.enums.Estado;
import com.sena.mesa.mesaayuda.enums.Prioridad;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    String titulo;

    String descripcion;

    Prioridad prioridad;

    Estado estado;

    LocalDateTime creadoEn;

    LocalDateTime slaVenceEn;

    @ManyToOne()
    @JoinColumn(name="usuario")
    Usuario creadoPor;


}
