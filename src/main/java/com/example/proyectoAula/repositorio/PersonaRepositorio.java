package com.example.proyectoAula.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyectoAula.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

    // Solo cuentas con contraseña (las creadas desde /personas no tienen)
    boolean existsByCorreoAndContrasenaIsNotNull(String correo);

    Optional<Persona> findFirstByCorreoAndContrasenaIsNotNull(String correo);
}