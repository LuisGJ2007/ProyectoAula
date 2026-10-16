package com.example.proyectoAula.servicio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.repositorio.PersonaRepositorio;

@Service
public class PersonaServicio {

    private final PersonaRepositorio repositorio;

    public PersonaServicio(PersonaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Persona> listar() {
        return repositorio.findAll();
    }

    public Persona obtener(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
    }

    public Persona crear(Persona persona) {
        return repositorio.save(persona);
    }

    public Persona actualizar(Long id, Persona datos) {
        Persona persona = obtener(id);
        return repositorio.save(persona);
    }

    public void eliminar(Long id) {
        Persona persona = obtener(id);
        repositorio.delete(persona);
    }

}