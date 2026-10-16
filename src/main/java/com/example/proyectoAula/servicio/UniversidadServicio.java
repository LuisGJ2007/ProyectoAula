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

    public List<Universidad> listar() {
        return repositorio.findAll();
    }

    public Universidad obtener(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Universidad no encontrada"));
    }

    public Universidad crear(Universidad universidad) {
        return repositorio.save(universidad);
    }

    public Universidad actualizar(Long id, Universidad datos) {
        Universidad universidad = obtener(id);
        return repositorio.save(universidad);
    }

    public void eliminar(Long id) {
        Universidad universidad = obtener(id);
        repositorio.delete(universidad);
    }
}
