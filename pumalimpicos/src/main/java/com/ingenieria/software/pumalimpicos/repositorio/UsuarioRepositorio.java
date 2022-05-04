package com.ingenieria.software.repositorio;
import com.ingenieria.software.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    boolean existsUsuarioByNombre(String nombre);
    Usuario findByNombre(String nombre);
}