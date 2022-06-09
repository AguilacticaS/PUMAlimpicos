package com.ingenieria.software.pumalimpicos.repositorio;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
	
	@Query("SELECT u FROM Usuario u WHERE u.rol = ?1")
	public List<Usuario> findAll(String palabraClave);
	
}