package com.example.proyectoAula.repositorio;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.proyectoAula.modelo.Universidad;

public interface UniversidadRepositorio extends JpaRepository<Universidad, String> {
    List<Universidad> findByNombre(String nombre);

    List<Universidad> findByMunicipio(String municipio);

    List<Universidad> findByTelefonoDeInstitucion(String telefonoDeInstitucion);
}