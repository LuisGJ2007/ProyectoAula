package com.example.proyectoAula.servicio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.Universidad;
import com.example.proyectoAula.repositorio.UniversidadRepositorio;

@Service
public class UniversidadServicio {

    private final UniversidadRepositorio repositorio;

    public UniversidadServicio(UniversidadRepositorio repositorio) {
        this.repositorio = repositorio;
    }

}
