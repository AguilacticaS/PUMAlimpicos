package com.ingenieria.software.pumalimpicos.controlador;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/menuAdministrador")
public class MenuAdministradorControlador {

    @GetMapping("/")
    public String index() {
        return "usuario/menuAdministrador";
    }

}