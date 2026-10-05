package com.example.proyectoAula.controlador;

import com.example.proyectoAula.modelo.persona;
import com.example.proyectoAula.repositorio.personaRepository;

@RestController
@RequestMapping("/api/personas")
public class personaController {

    private final personaRepository repositorio;

    public personaController(personaRepository repositorio) {
        this.repositorio = repositorio;
    }

    @GetMapping
    public List<persona> listar() {
        // TODO: devolver todas las personas
    }

    @GetMapping("/{id}")
    public persona obtener(@PathVariable Long id) {
        // TODO: buscar por id (¿qué pasa si no existe?)
    }

    @PostMapping
    public persona crear(@RequestBody persona persona) {
        // TODO: guardar y devolver la persona
    }

    @PutMapping("/{id}")
    public persona actualizar(@PathVariable Long id, @RequestBody persona datos) {
        // TODO: buscar, actualizar campos, guardar
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        // TODO: borrar por id
    }
}