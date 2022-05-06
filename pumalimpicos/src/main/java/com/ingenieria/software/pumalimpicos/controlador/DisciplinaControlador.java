package com.ingenieria.software.pumalimpicos.controlador;


import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/disciplinas")
public class DisciplinaControlador {

    private final DisciplinaServicio disciplinaServicio;

    @Autowired
    DisciplinaControlador(DisciplinaServicio disciplinaServicio) {
        this.disciplinaServicio = disciplinaServicio;
    }


    @GetMapping("/agregar")
    public String agregarDisciplina() {
        return "disciplina/agregar";
    }

    @PostMapping("/agregar")
    public String registra(HttpServletRequest request, Model model) {
        Disciplina nuevaDisciplina = new Disciplina(
                request.getParameter("nombre"),
                request.getParameter("clasificacion")            
            );
        disciplinaServicio.agregaDisciplina(nuevaDisciplina);
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/consultar";
    }

    @GetMapping("/eliminar")
    public String eliminarDisciplina(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/eliminar";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarDisciplina(@PathVariable("id") Long id, Model model) {
        model.addAttribute("disciplina", disciplinaServicio.getDisciplina(id));
        return "disciplina/eliminardisciplina";
    }

    @PostMapping("/eliminar")
    public String borra(HttpServletRequest request, Model model) {
        disciplinaServicio.eliminaDisciplina(Long.parseLong(request.getParameter("id"))); 
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/eliminar";
    }

    @GetMapping("/consultar")
    public String consultaDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/consultar";
    }



    @GetMapping("/actualizar")
    public String actualizaDisciplina(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/actualizar";
    }

    @GetMapping("/actualizar/{id}")
    public String actualizaDisciplina(@PathVariable("id") Long id, Model model) {
        model.addAttribute("disciplina", disciplinaServicio.getDisciplina(id));
        return "disciplina/actualizardisciplina";
    }

    @PostMapping("/actualizar")
    public String actualiza(HttpServletRequest request, Model model) {
        disciplinaServicio.actualizaDisciplina(Long.parseLong(request.getParameter("id")), 
                                               request.getParameter("nombre"),
                                               request.getParameter("clasificacion"));
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "disciplina/actualizar";
    }
}