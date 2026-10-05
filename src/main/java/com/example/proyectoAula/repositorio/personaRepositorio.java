package com.example.proyectoAula.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import main.java.com.example.proyectoAula.modelo.persona;
import java.util.List;

public interface personaRepositorio extends JpaRepository<persona, Long> {

    // TODO (opcional): consultas propias, por ejemplo:

List<persona> findByEstadoDeUsuario(String estadoDeUsuario);
    // List<persona> findByEstadoDeUsuario(String estadoDeUsuario);

}