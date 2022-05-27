package com.ingenieria.software.pumalimpicos.controlador;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.servicio.UsuarioServicio;

@Controller
public class LoginController{
	@Autowired
	private UsuarioServicio usuarioServicio;
	
	@GetMapping("/auth/login")
	public String Login(Model model){
		model.addAttribute("usuario", new Usuario());
		return "login";
	}

	@GetMapping("/auth/registro")
	public String registroForm(Model model){
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	@PostMapping("/auth/registro")
	public String registro(@Valid @ModelAttribute Usuario usuario, BindingResult result, Model model){
		if(result.hasErrors()){
			return "redirect:/auth/registro";
		}else{
			model.addAttribute("usuario",usuarioServicio.registrar(usuario));
		}
		return "redirect:/auth/login";
	}
}