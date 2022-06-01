package com.ingenieria.software.pumalimpicos.controlador;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.servicio.UsuarioServicio;

@Controller
@RequestMapping("/profile")
public class PrivateController{
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/")
	public String profile(Authentication auth, HttpSession session) {
		//nombre de usuario en la sesion 
		String username = auth.getName();
		if(session.getAttribute("usuario")==null) {
			Usuario usuario = usuarioServicio.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		return "profile";
	}
}