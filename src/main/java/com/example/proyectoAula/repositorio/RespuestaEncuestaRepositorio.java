package com.example.proyectoAula.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.proyectoAula.modelo.RespuestaEncuesta;

public interface RespuestaEncuestaRepositorio extends JpaRepository<RespuestaEncuesta, String> {
}