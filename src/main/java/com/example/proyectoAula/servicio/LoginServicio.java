package com.example.proyectoAula.servicio;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.proyectoAula.modelo.Persona;
import com.example.proyectoAula.repositorio.PersonaRepositorio;

@Service
public class LoginServicio {

    private final PersonaRepositorio personaRepositorio;
    private final PasswordEncoder passwordEncoder;

    public LoginServicio(PersonaRepositorio personaRepositorio, PasswordEncoder passwordEncoder) {
        this.personaRepositorio = personaRepositorio;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Persona> autenticar(String correo, String contrasena) {
        Optional<Persona> encontrada = personaRepositorio.findFirstByCorreoAndContrasenaIsNotNull(correo);
        if (encontrada.isEmpty()) {
            return Optional.empty();
        }

        Persona persona = encontrada.get();
        String guardada = persona.getContrasena();

        // Contraseña cifrada con BCrypt
        if (guardada.startsWith("$2")) {
            return passwordEncoder.matches(contrasena, guardada) ? encontrada : Optional.empty();
        }

        // Cuentas viejas en texto plano: se validan y se cifran
        if (guardada.equals(contrasena)) {
            persona.setContrasena(passwordEncoder.encode(contrasena));
            personaRepositorio.save(persona);
            return Optional.of(persona);
        }
        return Optional.empty();
    }

    public Persona registrar(String nombre, String correo, String contrasena) {
        if (personaRepositorio.existsByCorreoAndContrasenaIsNotNull(correo)) {
            throw new IllegalArgumentException("Ya existe una cuenta con ese correo");
        }

        Persona persona = new Persona();
        persona.setNombre(nombre);
        persona.setCorreo(correo);
        persona.setContrasena(passwordEncoder.encode(contrasena));
        persona.setEstadoDeUsuario("ACTIVO");
        return personaRepositorio.save(persona);
    }
}