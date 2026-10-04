package main.java.com.example.proyectoAula.repositorio;

public interface personaRepositorio extends JpaRepository<persona, Long> {

    // TODO (opcional): consultas propias, por ejemplo:
    // List<Persona> findByEstadoDeUsuario(String estadoDeUsuario);
}