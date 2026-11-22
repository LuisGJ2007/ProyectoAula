package com.example.proyectoAula.controlador;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.servicio.LoginServicio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginControlador {

    private final LoginServicio loginServicio;

    public LoginControlador(LoginServicio loginServicio) {
        this.loginServicio = loginServicio;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "html/login";
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String correo,
                                @RequestParam String contrasena,
                                HttpSession session,
                                Model model) {
        Optional<Persona> persona = loginServicio.autenticar(correo.trim(), contrasena);
        if (persona.isPresent()) {
            session.setAttribute("usuarioId", persona.get().getId());
            // El encabezado muestra este valor arriba a la izquierda
            session.setAttribute("nombreUsuario", persona.get().getNombre());
            return "redirect:/";
        }
        model.addAttribute("error", "Correo o contraseña incorrectos");
        return "html/login";
    }

    @GetMapping("/registro")
    public String mostrarRegistro() {
        return "html/registro";
    }

    @PostMapping("/registro")
    public String procesarRegistro(@RequestParam String nombre,
                                   @RequestParam String correo,
                                   @RequestParam String contrasena,
                                   Model model) {
        try {
            loginServicio.registrar(nombre.trim(), correo.trim(), contrasena);
            return "redirect:/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "html/registro";
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}