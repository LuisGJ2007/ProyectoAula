package com.example.proyectoAula.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectoAula.modelo.Data;

public interface DataRepositorio extends JpaRepository<Data, Long> {

    List<Data> findByEstadoDeUsuario(String estadoDeUsuario);
}