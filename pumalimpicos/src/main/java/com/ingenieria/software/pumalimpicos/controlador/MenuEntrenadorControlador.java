package com.ingenieria.software.pumalimpicos.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Clase MenuEntrenadorControlador, que controla la
 * comunicación entre la vista y el modelo.
 * @author Armando Ramírez
 * @version
 **/
@Controller
@RequestMapping(path = "/menuentrenador")
public class MenuEntrenadorControlador {

    @GetMapping("/")
    public String index() {
        return "competidor/menuentrenador";
    }
}
