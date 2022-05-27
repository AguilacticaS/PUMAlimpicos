package com.ingenieria.software.pumalimpicos.servicio;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;

public interface UsuarioServicio{
	public Usuario findByUsername(String username);
	public Usuario registrar(Usuario u);
}