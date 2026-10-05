package com.example.proyectoAula.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectoAula.modelo.Persona;

public interface PersonaRepositorio extends JpaRepository<Persona, Long> {

    List<Persona> findByEstadoDeUsuario(String estadoDeUsuario);
}