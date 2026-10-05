package main.java.com.example.proyectoAula.repositorio;

public interface personaRepositorio extends JpaRepository<persona, Long> {

    // List<Persona> findByEstadoDeUsuario(String estadoDeUsuario);
}