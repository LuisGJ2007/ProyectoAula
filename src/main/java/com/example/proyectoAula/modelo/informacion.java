package com.example.proyectoAula.modelo;

import java.util.*;

public class informacion {
    
    String nombre;
    String apellido;
    String correo;
    int idDeInforme;

    String informe;
    Boolean estadoDeInforme;
    
    public informacion(String nombre, String apellido, String correo, int idDeInforme, String informe, Boolean estadoDeInforme) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.idDeInforme = idDeInforme;
        this.informe = informe;
        this.estadoDeInforme = estadoDeInforme;
    }

    public int getIdDeInforme() {
        return idDeInforme;
    }

    public void setIdDeInforme(int idDeInforme) {
        this.idDeInforme = idDeInforme;
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

    public String getInforme() {
        return informe;
    }

    public void setInforme(String informe) {
        this.informe = informe;
    }

    public Boolean getEstadoDeInforme() {
        return estadoDeInforme;
    }

    public void setEstadoDeInforme(Boolean estadoDeInforme) {
        this.estadoDeInforme = estadoDeInforme;
    }

    public void estadoDeInforme(Boolean estadoDeInforme) {
        this.estadoDeInforme = estadoDeInforme;

        if (estadoDeInforme == true) {
            System.out.println("El informe esta resuleto.");

        } else if (estadoDeInforme == false) {
            System.out.println("El informe esta en desarrollo.");
            
        } else if (estadoDeInforme == null) {
            System.out.println("El estado del informe es desconocido.");
        }
    }

    List<informacion> listaDeInformes = new ArrayList<>();
    
}


