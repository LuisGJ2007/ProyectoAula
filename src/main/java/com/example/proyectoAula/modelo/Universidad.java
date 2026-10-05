package com.example.proyectoAula.modelo;

import jakarta.persistence.*;

@Entity
public class Universidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;
    private String municipio;
    private String correoInstitucional;
    private String telefonoDeInstitucion;

    public Universidad() {
    }

    public Universidad(String nombre, String municipio, String correoInstitucional, String telefonoDeInstitucion) {
        this.nombre = nombre;
        this.municipio = municipio;
        this.correoInstitucional = correoInstitucional;
        this.telefonoDeInstitucion = telefonoDeInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        this.correoInstitucional = correoInstitucional;
    }

    public String getTelefonoDeInstitucion() {
        return telefonoDeInstitucion;
    }

    public void setTelefonoDeInstitucion(String telefonoDeInstitucion) {
        this.telefonoDeInstitucion = telefonoDeInstitucion;
    }
    
}
