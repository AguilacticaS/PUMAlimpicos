package com.ingenieria.software.pumalimpicos.controlador;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.servicio.CompetidorServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Clase Entrenador Controlador.
 * Que comunica el modelo con al vista de la aplicación.
 * @author Armando Ramírez
 * @version
 **/
@Controller
@RequestMapping(path = "/competidores")
public class EntrenadorControlador {

    /*Atributos*/
    private final CompetidorServicio competidorServicio;

    /**
     * Constructor
     * @param competidorServicio
     **/
    @Autowired
    EntrenadorControlador(CompetidorServicio competidorServicio){
        this.competidorServicio = competidorServicio;
    }

    /**
     * Método para agregar un competidor.
     **/
    @GetMapping("/agregar")
    public String agregarCompetidor(){
        return "competidor/agregar";
    }

    @PostMapping("/agregar")
    public String registra(HttpServletRequest request, Model model){
        Competidor nuevoCompetidor = new Competidor(
                request.getParameter("nombre"),
                request.getParameter("disciplina")
        );
        competidorServicio.agregaCompetidor(nuevoCompetidor);
        model.addAttribute("competidores", competidorServicio.getCompetidores());
        return "competidor/consultar";
    }

    @GetMapping("/eliminar")
    public String eliminarCompetidor(Model model){
        model.addAttribute("competidores",competidorServicio.getCompetidores());
        return "competidor/eliminar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCompetidor(@PathVariable("id") Long id, Model model){
        model.addAttribute("competidor", competidorServicio.getCompetidor(id));
        return "competidor/eliminarcompetidor";
    }

    @PostMapping("/eliminar")
    public String borra(HttpServletRequest request, Model model){
        competidorServicio.eliminaCompetidor(Long.parseLong(request.getParameter("id")));
        model.addAttribute("competidores",competidorServicio.getCompetidores());
        return "competidor/eliminar";
    }

    @GetMapping("/consultar")
    public String consultaCompetidor(Model model){
        model.addAttribute("competidores",competidorServicio.getCompetidores());
        return "competidor/consultar";
    }

    @GetMapping("/actualizar")
    public String actualizaCompetidor(Model model){
        model.addAttribute("competidores",competidorServicio.getCompetidores());
        return "competidor/actualizar";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizaCompetidor(@PathVariable("id") Long id, Model model){
        model.addAttribute("competidor",competidorServicio.getCompetidor(id));
        return "competidor/actualizarcompetidor";
    }

    @PostMapping("/actualizar")
    public String actualiza(HttpServletRequest request, Model model){
        competidorServicio.actualizaCompetidor(Long.parseLong(request.getParameter("id")),
                                               request.getParameter("nombre"),
                                               request.getParameter("disciplina"));
        model.addAttribute("competidores", competidorServicio.getCompetidores());
        return "competidor/actualizar";
    }
}
