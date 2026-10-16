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

    public Universidad obtener(String correoInstitucional) {
        List<Universidad> universidades = repositorio.findByCorreoInstitucional(correoInstitucional);
        if (universidades.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Universidad no encontrada");
        }
        return universidades.get(0);
    }

    public Universidad crear(Universidad universidad) {
        return repositorio.save(universidad);
    }

    public Universidad actualizar(String correoInstitucional, Universidad datos) {
        Universidad universidad = obtener(correoInstitucional);
        return repositorio.save(universidad);
    }

    public void eliminar(String correoInstitucional) {
        Universidad universidad = obtener(correoInstitucional);
        repositorio.delete(universidad);
    }

}
