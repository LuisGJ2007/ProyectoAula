package com.example.proyectoAula.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class RespuestaEncuesta {

    @Id
    private String codigo;

    private String universidad;
    private String programaAcademico;
    private Integer semestre;
    private String situacionLaboral;
    private String frecuenciaEstres;
    private String horasSueno;
    private String nivelConcentracion;
    private String apoyoEmocional;
    private String estrategiaEstres;
    private String satisfaccionBienestar;
    private String estresAfectaRendimiento;
    private String consideroApoyoPsicologico;
    private String cargaAcademica;
    @Column(length = 500)
    private String comentario;

    public RespuestaEncuesta() {
    }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }

    public String getProgramaAcademico() { return programaAcademico; }
    public void setProgramaAcademico(String programaAcademico) { this.programaAcademico = programaAcademico; }

    public Integer getSemestre() { return semestre; }
    public void setSemestre(Integer semestre) { this.semestre = semestre; }

    public String getSituacionLaboral() { return situacionLaboral; }
    public void setSituacionLaboral(String situacionLaboral) { this.situacionLaboral = situacionLaboral; }

    public String getFrecuenciaEstres() { return frecuenciaEstres; }
    public void setFrecuenciaEstres(String frecuenciaEstres) { this.frecuenciaEstres = frecuenciaEstres; }

    public String getHorasSueno() { return horasSueno; }
    public void setHorasSueno(String horasSueno) { this.horasSueno = horasSueno; }

    public String getNivelConcentracion() { return nivelConcentracion; }
    public void setNivelConcentracion(String nivelConcentracion) { this.nivelConcentracion = nivelConcentracion; }

    public String getApoyoEmocional() { return apoyoEmocional; }
    public void setApoyoEmocional(String apoyoEmocional) { this.apoyoEmocional = apoyoEmocional; }

    public String getEstrategiaEstres() { return estrategiaEstres; }
    public void setEstrategiaEstres(String estrategiaEstres) { this.estrategiaEstres = estrategiaEstres; }

    public String getSatisfaccionBienestar() { return satisfaccionBienestar; }
    public void setSatisfaccionBienestar(String satisfaccionBienestar) { this.satisfaccionBienestar = satisfaccionBienestar; }

    public String getEstresAfectaRendimiento() { return estresAfectaRendimiento; }
    public void setEstresAfectaRendimiento(String estresAfectaRendimiento) { this.estresAfectaRendimiento = estresAfectaRendimiento; }

    public String getConsideroApoyoPsicologico() { return consideroApoyoPsicologico; }
    public void setConsideroApoyoPsicologico(String consideroApoyoPsicologico) { this.consideroApoyoPsicologico = consideroApoyoPsicologico; }

    public String getCargaAcademica() { return cargaAcademica; }
    public void setCargaAcademica(String cargaAcademica) { this.cargaAcademica = cargaAcademica; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

}