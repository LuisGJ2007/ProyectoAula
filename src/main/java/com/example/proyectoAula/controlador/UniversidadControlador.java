package com.example.proyectoAula.controlador;


import com.example.proyectoAula.modelo.Universidad;
import com.example.proyectoAula.servicio.*;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/universidades")
public class UniversidadControlador {
    
    private final UniversidadServicio servicio;

    public UniversidadControlador(UniversidadServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("universidades", servicio.listar());
        return "html/universidades/lista";
    }

    @GetMapping("/nueva")
    public String formularioNueva(Model model) {
        model.addAttribute("universidad", new Universidad());
        model.addAttribute("modoEdicion", false);
        return "html/universidades/formulario";
    }

    @GetMapping("/editar")
    public String formularioEditar(@RequestParam String correoInstitucional, Model model) {
        model.addAttribute("universidad", servicio.obtener(correoInstitucional));
        model.addAttribute("modoEdicion", true);
        return "html/universidades/formulario";
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute Universidad universidad, Model model) {
        try {
            servicio.crear(universidad);
            return "redirect:/universidades";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            model.addAttribute("modoEdicion", false);
            return "html/universidades/formulario";
        }
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute Universidad universidad, Model model) {
        try {
            servicio.actualizar(universidad.getCorreoInstitucional(), universidad);
            return "redirect:/universidades";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            model.addAttribute("modoEdicion", true);
            return "html/universidades/formulario";
        }
    }

    @GetMapping("/eliminar")
    public String eliminar(@RequestParam String correo) {
        servicio.eliminar(correo);
        return "redirect:/universidades";
    }
}
