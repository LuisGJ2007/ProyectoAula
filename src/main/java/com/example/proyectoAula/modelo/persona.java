package com.example.proyectoAula.modelo;

import jakarta.persistence.*;

@Entity
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaso;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String estadoDeUsuario;

    // TODO: constructor vacío (JPA lo exige)
    // TODO: getters y setters de cada campo
}