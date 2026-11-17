package com.example.proyectoAula.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.Data;
import com.example.proyectoAula.servicio.DataServicio;

@Controller
@RequestMapping("/data")
public class DataControlador {

    private final DataServicio servicio;

    public DataControlador(DataServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("informes", servicio.listar());
        return "html/listas/Datalista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("data", new Data());
        return "html/formularios/Dataformulario";
    }

    @GetMapping("/editar/{id}")
    public String formularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute("data", servicio.obtener(id));
        return "html/formularios/Dataformulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Data data, Model model) {
        try {
            if (data.getId() == null) {
                servicio.crear(data);
            } else {
                servicio.actualizar(data.getId(), data);
            }
            return "redirect:/data";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            return "html/formularios/Dataformulario";
        }
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        servicio.eliminar(id);
        return "redirect:/data";
    }
}