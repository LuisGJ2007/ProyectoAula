package com.example.proyectoAula.controlador;

import java.util.List;

import com.example.proyectoAula.modelo.Universidad;
import com.example.proyectoAula.repositorio.UniversidadRepositorio;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/universidad")
public class UniversidadControlador {
    
    private final UniversidadRepositorio repositorio;

    public UniversidadControlador(UniversidadRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<Universidad> listar() {
        return repositorio.findAll();
    }

    @GetMapping("/{id}")
    public Universidad obtener(@PathVariable Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Universidad no encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Universidad crear(@RequestBody Universidad universidad) {
        return repositorio.save(universidad);
    }

    @PutMapping("/{id}")
    public Universidad actualizar(@PathVariable Long id, @RequestBody Universidad datos) {
        Universidad existente = obtener(id);
        existente.setNombre(datos.getNombre());
        existente.setCorreoInstitucional(datos.getCorreoInstitucional());
        existente.setTelefonoDeInstitucion(datos.getTelefonoDeInstitucion());
        existente.setMunicipio(datos.getMunicipio());
        return repositorio.save(existente);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        if (!repositorio.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Universidad no encontrada");
        }
        repositorio.deleteById(id);
    }
}
