package com.ingenieria.software.pumalimpicos.controlador;

import com.ingenieria.software.pumalimpicos.servicio.CompetidorServicio;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/pos")
public class CompetidorControlador {

    /*Atributos*/
    private final CompetidorServicio competidorServicio;
    private final DisciplinaServicio disciplinaServicio;

    /**
     * Constructor
     * @param competidorServicio
     **/
    @Autowired
    CompetidorControlador(CompetidorServicio competidorServicio,
                          DisciplinaServicio disciplinaServicio){
        this.competidorServicio = competidorServicio;
        this.disciplinaServicio = disciplinaServicio;
    }

    @GetMapping("/posiciones")
    public String  posicionesCompetidor(Model model){
        model.addAttribute("competidores",competidorServicio.getCompetidoresPorCalif());
        return "competidor/posiciones";
    }
}
