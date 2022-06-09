package com.ingenieria.software.pumalimpicos.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.repositorio.UsuarioRepositorio;

@Service
public class UsuarioServicio{
	
	private final UsuarioRepositorio usuarioRepositorio;
	 
	 @Autowired
	 public UsuarioServicio(UsuarioRepositorio usuarioRepositorio ){
		 this.usuarioRepositorio=usuarioRepositorio;
	 }
	 
	 public List<Usuario> getUsuarios(String palabraClave){
		 	if(palabraClave != null){
		 		return usuarioRepositorio.findAll(palabraClave);
		 	}
			return usuarioRepositorio.findAll();
		}
	 
	public Usuario get(Long id){
		return usuarioRepositorio.findById(id).get();
	}
	 
	 public Usuario getUsuario(Long usuarioId) {
	        return usuarioRepositorio.findById(usuarioId)
	        .orElseThrow(() -> new IllegalStateException("No hay usuario registrado con el id."));
	    }
}
