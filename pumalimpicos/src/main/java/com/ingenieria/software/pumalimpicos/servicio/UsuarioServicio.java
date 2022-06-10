package com.ingenieria.software.pumalimpicos.servicio;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import java.util.List;

public interface UsuarioServicio{
	public Usuario findByUsername(String username);
    public  List<Usuario> getUsuarios();
	public Usuario registrar(Usuario u);
}