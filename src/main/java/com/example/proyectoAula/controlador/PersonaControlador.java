package com.example.proyectoAula.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.servicio.PersonaServicio;

@Controller
@RequestMapping("/personas")
public class PersonaControlador {

    private final PersonaServicio servicio;

    public PersonaControlador(PersonaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("personas", servicio.listar());
        return "html/personas/listas";
    }

    @GetMapping("/nueva")
    public String formularioNueva(Model model) {
        model.addAttribute("persona", new Persona());
        return "html/personas/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Persona persona) {
            if (persona.getId() == null) {
                servicio.crear(persona);
            } else {
                servicio.actualizar(persona.getId(), persona);
            }
        return "redirect:/personas";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("persona", servicio.obtener(id));
        return "html/personas/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        
        return "redirect:/personas";
    }
}