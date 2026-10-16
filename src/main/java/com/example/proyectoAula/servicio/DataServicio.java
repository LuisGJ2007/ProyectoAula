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

    public Data crear(Data data) {
        return repositorio.save(data);
    }

    public Data actualizar(Long id, Data datos) {
        Data data = obtener(id);
        return repositorio.save(data);
    }

    public void eliminar(Long id) {
        Data data = obtener(id);
        repositorio.delete(data);
    }
    
}
