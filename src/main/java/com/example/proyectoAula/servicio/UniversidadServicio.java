package com.example.proyectoAula.servicio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.Universidad;
import com.example.proyectoAula.repositorio.UniversidadRepositorio;

@Service
public class UniversidadServicio {

    private final UniversidadRepositorio repositorio;

    public UniversidadServicio(UniversidadRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Universidad> listar() {
        return repositorio.findAll();
    }

    public Universidad obtener(String correoInstitucional) {
        return repositorio.findById(correoInstitucional)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Universidad no encontrada"));
    }

    private void validar(Universidad universidad) {
        if (universidad.getNombre() == null || universidad.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacío");
        }
        if (universidad.getCorreoInstitucional() == null || universidad.getCorreoInstitucional().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo institucional no puede estar vacío");
        }
        if (universidad.getMunicipio() == null || universidad.getMunicipio().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El municipio no puede estar vacío");
        }
        if (universidad.getTelefonoDeInstitucion() == null || universidad.getTelefonoDeInstitucion().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El teléfono de la institución no puede estar vacío");
        }
    }

    public Universidad crear(Universidad universidad) {
        validar(universidad);
        if (repositorio.existsById(universidad.getCorreoInstitucional())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una universidad con ese correo");
        }
        return repositorio.save(universidad);
    }

    public Universidad actualizar(String correoInstitucional, Universidad datos) {
        validar(datos);
        Universidad existente = obtener(correoInstitucional);
        existente.setNombre(datos.getNombre());
        existente.setMunicipio(datos.getMunicipio());
        existente.setTelefonoDeInstitucion(datos.getTelefonoDeInstitucion());
        return repositorio.save(existente);
    }

    public void eliminar(String correoInstitucional) {
        repositorio.delete(obtener(correoInstitucional));
        
    }

}
