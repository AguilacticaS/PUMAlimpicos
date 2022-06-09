package com.ingenieria.software.pumalimpicos.controlador;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.servicio.UsuarioServicio;

@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioControlador {
	private final UsuarioServicio usuarioServicio;

	@Autowired
    UsuarioControlador(UsuarioServicio usuarioServicio) {
        this.usuarioServicio = usuarioServicio;
    }
    

    @GetMapping("/consultar")
    public String consultarUsuario(Model model, @Param("palabraClave") String palabraClave){
    	
    	List<Usuario> usuarios = usuarioServicio.getUsuarios(palabraClave);
    	model.addAttribute("usuarios", usuarios);
    	model.addAttribute("palabraClave", palabraClave);
    	return "usuario/consultar";
    
}
}