package com.example.proyectoAula.servicio;

import java.util.Comparator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.proyectoAula.modelo.RespuestaEncuesta;
import com.example.proyectoAula.repositorio.RespuestaEncuestaRepositorio;

@Service
public class RespuestaEncuestaServicio {

    private final RespuestaEncuestaRepositorio repositorio;

    public RespuestaEncuestaServicio(RespuestaEncuestaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<RespuestaEncuesta> listar(String frecuencia, Integer semestre) {
        return repositorio.findAll().stream()
                .filter(r -> frecuencia == null || frecuencia.isBlank()
                        || frecuencia.equals(r.getFrecuenciaEstres()))
                .filter(r -> semestre == null || semestre.equals(r.getSemestre()))
                .sorted(Comparator.comparing(RespuestaEncuesta::getCodigo))
                .toList();
    }

    public List<Integer> semestres() {
        return repositorio.findAll().stream()
                .map(RespuestaEncuesta::getSemestre)
                .distinct()
                .sorted()
                .toList();
    }

    public RespuestaEncuesta obtener(String codigo) {
        return repositorio.findById(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Respuesta no encontrada"));
    }

    public RespuestaEncuesta crear(RespuestaEncuesta respuesta) {
        validar(respuesta);
        respuesta.setCodigo(generarCodigo());
        return repositorio.save(respuesta);
    }

    public RespuestaEncuesta actualizar(String codigo, RespuestaEncuesta datos) {
        validar(datos);
        RespuestaEncuesta existente = obtener(codigo);

        existente.setUniversidad(datos.getUniversidad());
        existente.setProgramaAcademico(datos.getProgramaAcademico());
        existente.setSemestre(datos.getSemestre());
        existente.setSituacionLaboral(datos.getSituacionLaboral());
        existente.setFrecuenciaEstres(datos.getFrecuenciaEstres());
        existente.setHorasSueno(datos.getHorasSueno());
        existente.setNivelConcentracion(datos.getNivelConcentracion());
        existente.setApoyoEmocional(datos.getApoyoEmocional());
        existente.setEstrategiaEstres(datos.getEstrategiaEstres());
        existente.setSatisfaccionBienestar(datos.getSatisfaccionBienestar());
        existente.setEstresAfectaRendimiento(datos.getEstresAfectaRendimiento());
        existente.setConsideroApoyoPsicologico(datos.getConsideroApoyoPsicologico());
        existente.setCargaAcademica(datos.getCargaAcademica());
        existente.setComentario(datos.getComentario());

        return repositorio.save(existente);
    }

    public void eliminar(String codigo) {
        repositorio.delete(obtener(codigo));
    }

    // Las respuestas agregadas desde la página reciben códigos WEB-001, WEB-002...
    private String generarCodigo() {
        int max = repositorio.findAll().stream()
                .map(RespuestaEncuesta::getCodigo)
                .filter(c -> c != null && c.startsWith("WEB-"))
                .mapToInt(c -> {
                    try {
                        return Integer.parseInt(c.substring(4));
                    } catch (NumberFormatException e) {
                        return 0;
                    }
                })
                .max()
                .orElse(0);
        return String.format("WEB-%03d", max + 1);
    }

    private void validar(RespuestaEncuesta r) {
        exigir(r.getUniversidad(), "La universidad no puede estar vacía");
        exigir(r.getProgramaAcademico(), "El programa académico no puede estar vacío");
        if (r.getSemestre() == null || r.getSemestre() < 1) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El semestre no es válido");
        }
        exigir(r.getSituacionLaboral(), "Selecciona la situación laboral");
        exigir(r.getFrecuenciaEstres(), "Selecciona la frecuencia de estrés");
        exigir(r.getHorasSueno(), "Selecciona las horas de sueño");
        exigir(r.getNivelConcentracion(), "Selecciona el nivel de concentración");
        exigir(r.getApoyoEmocional(), "Selecciona la percepción de apoyo emocional");
        exigir(r.getEstrategiaEstres(), "Selecciona la estrategia para manejar el estrés");
        exigir(r.getSatisfaccionBienestar(), "Selecciona la satisfacción con bienestar universitario");
        exigir(r.getEstresAfectaRendimiento(), "Indica si el estrés afecta el rendimiento");
        exigir(r.getConsideroApoyoPsicologico(), "Indica si ha considerado buscar apoyo psicológico");
        exigir(r.getCargaAcademica(), "Selecciona qué tan manejable es la carga académica");

        if (r.getComentario() != null && r.getComentario().length() > 500) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "El comentario no puede superar los 500 caracteres");
        }
    }

    private void exigir(String valor, String mensaje) {
        if (valor == null || valor.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, mensaje);
        }
    }
}