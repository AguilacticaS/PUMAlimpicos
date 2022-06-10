package com.ingenieria.software.pumalimpicos.controlador;


import com.ingenieria.software.pumalimpicos.servicio.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Clase Uusario Controlador.
 * Que comunica el modelo con al vista de la aplicación.
 * @author Marco
 * @version
 **/
@Controller
@RequestMapping(path = "/usuarios")
public class UsuarioControlador {

    /*Atributos*/
	private UsuarioServicio usuarioServicio;

    /**
     * Constructor
     * @param usuarioServicios
     **/
    @Autowired
    UsuarioControlador(UsuarioServicio usuarioServicio){
        this.usuarioServicio = usuarioServicio;
    }

    @GetMapping("/consultar")
    public String consultaCompetidor(Model model){
        model.addAttribute("usuarios", usuarioServicio.getUsuarios());
        return "usuario/consultar";
    }

}
