package com.example.proyectoAula.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectoAula.modelo.Universidad;

public interface UniversidadRepositorio extends JpaRepository<Universidad, Long> {

    List<Universidad> findByEstadoDeUsuario(String estadoDeUsuario);
}