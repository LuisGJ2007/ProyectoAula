package com.example.proyectoAula.servicio;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.Data;
import com.example.proyectoAula.repositorio.DataRepositorio;

@Service
public class DataServicio {
    
    private final DataRepositorio repositorio;

    public DataServicio(DataRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Data> listar() {
        return repositorio.findAll();
    }

    public Data obtener(Long id) {
        return repositorio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Data no encontrada"));
    }

    public List<Data> obtenerPorIdDeInforme(int idDeInforme) {
        return repositorio.findByIdDeInforme(idDeInforme);
    }

    private void validar(Data data) {
        if (data.getId() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id no puede ser cero");
        }
        else if (data.getId() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id no puede ser negativo");
        }
        else if (data.getNombre() == null || data.getNombre().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre no puede estar vacío");
        }
        else if (data.getApellido() == null || data.getApellido().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El apellido no puede estar vacío");
        }
        else if (data.getCorreo() == null || data.getCorreo().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo no puede estar vacío");
        }
        else if (data.getInforme() == null || data.getInforme().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El informe no puede estar vacío");
        }
        else if (data.getEstadoDeInforme() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El estadoDeInforme no puede ser nulo");
        }
    }
    public Data crear(Data data) {
        validar(data);
        return repositorio.save(data);
    }

    public Data actualizar(Long id, Data datos) {
        validar(datos);
        Data data = obtener(id);
        data.setIdDeInforme(datos.getIdDeInforme());
        data.setNombre(datos.getNombre());
        data.setApellido(datos.getApellido());
        data.setCorreo(datos.getCorreo());
        data.setInforme(datos.getInforme());
        data.setEstadoDeInforme(datos.getEstadoDeInforme());
        return repositorio.save(data);
    }

    public void eliminar(Long id) {
        Data data = obtener(id);
        repositorio.delete(data);
    }
    
}
