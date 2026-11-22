package com.example.proyectoAula.servicio;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.example.proyectoAula.modelo.RespuestaEncuesta;
import com.example.proyectoAula.repositorio.RespuestaEncuestaRepositorio;

@Service
public class DatasetServicio {

    private final RespuestaEncuestaRepositorio repositorio;

    public DatasetServicio(RespuestaEncuestaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<RespuestaEncuesta> filtrar(String universidad, String programa, Integer semestre) {
        return repositorio.findAll().stream()
                .filter(r -> universidad == null || universidad.equals(r.getUniversidad()))
                .filter(r -> programa == null || programa.equals(r.getProgramaAcademico()))
                .filter(r -> semestre == null || semestre.equals(r.getSemestre()))
                .sorted(Comparator.comparing(RespuestaEncuesta::getCodigo))
                .toList();
    }

    public List<String> universidades() {
        return repositorio.findAll().stream()
                .map(RespuestaEncuesta::getUniversidad)
                .filter(Objects::nonNull).distinct().sorted().toList();
    }

    public List<String> programas() {
        return repositorio.findAll().stream()
                .map(RespuestaEncuesta::getProgramaAcademico)
                .filter(Objects::nonNull).distinct().sorted().toList();
    }

    public List<Integer> semestres() {
        return repositorio.findAll().stream()
                .map(RespuestaEncuesta::getSemestre)
                .filter(Objects::nonNull).distinct().sorted().toList();
    }
}
