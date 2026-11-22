package com.example.proyectoAula.controlador;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyectoAula.modelo.RespuestaEncuesta;
import com.example.proyectoAula.servicio.DatasetServicio;

// Ruta /dataset (distinta de /data, que es el CRUD de informes).
@Controller
@RequestMapping("/dataset")
public class DatasetControlador {

    private final DatasetServicio servicio;

    public DatasetControlador(DatasetServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(@RequestParam(required = false) String universidad,
                         @RequestParam(required = false) String programa,
                         @RequestParam(required = false) Integer semestre,
                         Model model) {

        String uni = (universidad == null || universidad.isBlank()) ? null : universidad;
        String prog = (programa == null || programa.isBlank()) ? null : programa;

        List<RespuestaEncuesta> registros = servicio.filtrar(uni, prog, semestre);

        model.addAttribute("registros", registros);
        model.addAttribute("total", registros.size());
        model.addAttribute("universidades", servicio.universidades());
        model.addAttribute("programas", servicio.programas());
        model.addAttribute("semestres", servicio.semestres());
        model.addAttribute("universidadSel", uni);
        model.addAttribute("programaSel", prog);
        model.addAttribute("semestreSel", semestre);
        return "html/listas/Datasetlista";
    }
}
