package com.ingenieria.software.pumalimpicos.servicio.implementacion;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.repositorio.UsuarioRepositorio;
import com.ingenieria.software.pumalimpicos.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepositorio.findByUsername(username);
	}

	@Override
    public  List<Usuario> getUsuarios() {
        return usuarioRepositorio.findAll();
    }

	@Override
	public Usuario registrar(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuarioRepositorio.save(u);
	}
	
}