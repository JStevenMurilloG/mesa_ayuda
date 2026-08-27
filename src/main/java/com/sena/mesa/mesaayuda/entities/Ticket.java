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

    @Column(nullable = false)
    String titulo;

    @Column(nullable = false)
    String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Prioridad prioridad;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Estado estado;

    LocalDateTime creadoEn;

    LocalDateTime slaVenceEn;

    @ManyToOne()
    @JoinColumn(name="usuario")
    Usuario creadoPor;


}
