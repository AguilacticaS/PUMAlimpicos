package com.ingenieria.software.pumalimpicos.controlador;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.servicio.CompetidorServicio;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    private final DisciplinaServicio disciplinaServicio;

    /**
     * Constructor
     * @param competidorServicio
     **/
    @Autowired
    EntrenadorControlador(CompetidorServicio competidorServicio,
    DisciplinaServicio disciplinaServicio){
        this.competidorServicio = competidorServicio;
        this.disciplinaServicio = disciplinaServicio;
    }


    @GetMapping("/calificacion")
    public String calificacionCompetidor(){
        return "competidor/calificacion";
    }

    /**
     * Método para agregar un competidor.
     **/
    @GetMapping("/agregar")
    public String agregarCompetidor(Model model){
		model.addAttribute("competidor", new Competidor());
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "competidor/agregar";
    }

    @PostMapping("/agregar")
    public String registra(@Valid @ModelAttribute Competidor competidor, BindingResult result, Model model){
		if(result.hasErrors()){
			return "redirect:competidor/agregar";
		}else{
            competidorServicio.agregaCompetidor(competidor);
		}
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
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "competidor/actualizarcompetidor";
    }

    @PostMapping("/actualizar")
    public String actualiza(HttpServletRequest request, Model model){
        competidorServicio.actualizaCompetidor(Long.parseLong(request.getParameter("id")),
                                               request.getParameter("nombre"),
                                               request.getParameter("apellidoP"),
                                               request.getParameter("apellidoM"),
                                               request.getParameter("email"),
                                               request.getParameter("username"),
                                               request.getParameter("password"),
                                               request.getParameter("disciplina"));
        model.addAttribute("competidores", competidorServicio.getCompetidores());
        return "competidor/actualizar";
    }
}
