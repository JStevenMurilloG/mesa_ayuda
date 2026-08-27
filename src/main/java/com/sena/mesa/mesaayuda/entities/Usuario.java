package com.sena.mesa.mesaayuda.entities;

import com.sena.mesa.mesaayuda.enums.Rol;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    String nombre;

    @Column(unique = true)
    String email;

    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    Rol rol = Rol.USUARIO;





}
