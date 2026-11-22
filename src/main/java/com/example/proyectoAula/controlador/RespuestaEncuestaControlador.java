package com.example.proyectoAula.controlador;

import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.RespuestaEncuesta;
import com.example.proyectoAula.servicio.RespuestaEncuestaServicio;

@Controller
@RequestMapping("/encuesta")
public class RespuestaEncuestaControlador {

    private static final String UNIVERSIDAD = "Fundación Universitaria Tecnológico Comfenalco";

    private static final List<String> FRECUENCIAS =
            List.of("Nunca", "Rara vez", "Algunas veces", "Frecuentemente", "Siempre");

    private final RespuestaEncuestaServicio servicio;

    public RespuestaEncuestaControlador(RespuestaEncuestaServicio servicio) {
        this.servicio = servicio;
    }

    // ---------- Lista ----------
    @GetMapping
    public String listar(@RequestParam(required = false) String frecuencia,
                         @RequestParam(required = false) Integer semestre,
                         Model model) {
        List<RespuestaEncuesta> respuestas = servicio.listar(frecuencia, semestre);

        model.addAttribute("respuestas", respuestas);
        model.addAttribute("total", respuestas.size());
        model.addAttribute("frecuencias", FRECUENCIAS);
        model.addAttribute("semestres", servicio.semestres());
        model.addAttribute("frecuenciaSel", frecuencia);
        model.addAttribute("semestreSel", semestre);
        return "html/listas/Encuestalista";
    }

    // ---------- Agregar ----------
    @GetMapping("/nueva")
    public String formularioNueva(Model model) {
        RespuestaEncuesta respuesta = new RespuestaEncuesta();
        respuesta.setUniversidad(UNIVERSIDAD);
        model.addAttribute("respuesta", respuesta);
        return formulario(model, false);
    }

    @PostMapping("/crear")
    public String crear(@ModelAttribute("respuesta") RespuestaEncuesta respuesta, Model model) {
        try {
            servicio.crear(respuesta);
            return "redirect:/encuesta";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            return formulario(model, false);
        }
    }

    // ---------- Editar ----------
    @GetMapping("/editar")
    public String formularioEditar(@RequestParam String codigo, Model model) {
        model.addAttribute("respuesta", servicio.obtener(codigo));
        return formulario(model, true);
    }

    @PostMapping("/actualizar")
    public String actualizar(@ModelAttribute("respuesta") RespuestaEncuesta respuesta, Model model) {
        try {
            servicio.actualizar(respuesta.getCodigo(), respuesta);
            return "redirect:/encuesta";
        } catch (ResponseStatusException e) {
            model.addAttribute("error", e.getReason());
            return formulario(model, true);
        }
    }

    // ---------- Eliminar ----------
    @GetMapping("/eliminar")
    public String eliminar(@RequestParam String codigo) {
        servicio.eliminar(codigo);
        return "redirect:/encuesta";
    }

    // ---------- Opciones de los desplegables ----------
    private String formulario(Model model, boolean modoEdicion) {
        model.addAttribute("modoEdicion", modoEdicion);
        model.addAttribute("programas", List.of("Administración", "Contaduría", "Desarrollo de Software",
                "Ingeniería de Sistemas", "Seguridad y Salud en el Trabajo"));
        model.addAttribute("semestresOpc", List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        model.addAttribute("situaciones", List.of("No trabaja", "Trabajo ocasional", "Medio tiempo", "Tiempo completo"));
        model.addAttribute("frecuencias", FRECUENCIAS);
        model.addAttribute("sueno", List.of("Menos de 5 horas", "5 a 6 horas", "6 a 7 horas", "7 a 8 horas", "Más de 8 horas"));
        model.addAttribute("concentraciones", List.of("Muy baja", "Baja", "Regular", "Buena", "Muy buena"));
        model.addAttribute("apoyos", List.of("No", "Casi nunca", "A veces", "La mayoría de veces", "Sí, siempre"));
        model.addAttribute("estrategias", List.of("Busco apoyo psicológico", "Descanso o duermo", "Escucho música",
                "Hablo con alguien", "Hago ejercicio", "Organizo mi tiempo", "No hago nada en particular"));
        model.addAttribute("satisfacciones", List.of("Muy insatisfecho/a", "Insatisfecho/a", "Neutral",
                "Satisfecho/a", "Muy satisfecho/a"));
        model.addAttribute("rendimientos", List.of("No", "Poco", "Sí, algo", "Sí, mucho"));
        model.addAttribute("psicologicos", List.of("No", "No lo sé", "Sí"));
        model.addAttribute("cargas", List.of("Muy difícil", "Difícil", "Regular", "Manejable", "Muy manejable"));
        return "html/formularios/Encuestaformulario";
    }
}