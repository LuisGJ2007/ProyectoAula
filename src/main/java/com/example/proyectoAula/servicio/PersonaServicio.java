package com.example.proyectoAula.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.repositorio.PersonaRepositorio;

@Service
public class PersonaServicio {

    private final PersonaRepositorio personaRepositorio;

    public PersonaServicio(PersonaRepositorio personaRepositorio) {
        this.personaRepositorio = personaRepositorio;
    }

    public List<Persona> listar() {
        return personaRepositorio.findAll();
    }

    public Persona obtener(Long id) {
        return personaRepositorio.findById(id).orElse(null);
    }

    public Persona crear(Persona persona) {
        return personaRepositorio.save(persona);
    }

    public Persona actualizar(Long id, Persona datos) {
        Persona persona = personaRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Persona no encontrada"));

        persona.setNombre(datos.getNombre());
        persona.setApellido(datos.getApellido());
        persona.setCorreo(datos.getCorreo());
        persona.setTelefono(datos.getTelefono());
        persona.setEstadoDeUsuario(datos.getEstadoDeUsuario());

        return personaRepositorio.save(persona);
    }

    public void eliminar(Long id) {
        personaRepositorio.deleteById(id);
    }
}