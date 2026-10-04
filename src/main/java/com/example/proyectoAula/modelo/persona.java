package com.example.proyectoAula.modelo;

import jakarta.persistence.*;

@Entity
public class persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaso;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String estadoDeUsuario;

    public persona() {
    }

    public persona(String nombre, String apellido, String correo, String telefono, String estadoDeUsuario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.estadoDeUsuario = estadoDeUsuario;
    }

    public Long getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(Long idCaso) {
        this.idCaso = idCaso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoDeUsuario() {
        return estadoDeUsuario;
    }

    public void setEstadoDeUsuario(String estadoDeUsuario) {
        this.estadoDeUsuario = estadoDeUsuario;
    }

}