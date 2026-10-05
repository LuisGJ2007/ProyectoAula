package com.example.proyectoAula.controlador;

import java.util.List;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.repositorio.PersonaRepositorio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/data")
public class DataControlador {
    
    private final PersonaRepositorio repositorio;

    public DataControlador(PersonaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Persona> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Persona obtener(@PathVariable Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada"));
    }

    @PostMapping
    public Persona crear(@RequestBody Persona persona) {
        return repositorio.save(persona);
    }

    @PutMapping("/{id}")
    public Persona actualizar(@PathVariable Long id, @RequestBody Persona datos) {
        Persona existente = obtener(id);
        existente.setNombre(datos.getNombre());
        existente.setApellido(datos.getApellido());
        existente.setCorreo(datos.getCorreo());
        existente.setTelefono(datos.getTelefono());
        existente.setEstadoDeUsuario(datos.getEstadoDeUsuario());
        return repositorio.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Persona no encontrada");
        }
        repositorio.deleteById(id);
    }
}
