package com.ingenieria.software.pumalimpicos.controlador;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ingenieria.software.pumalimpicos.modelo.Disciplin;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/disciplinas")
public class DisciplinaControlador {

    private final DisciplinaServicio disciplinaServicio;

    @Autowired
    DisciplinaControlador(DisciplinaServicio disciplinaServicio) {
        this.disciplinaServicio = disciplinaServicio;
    }

    @GetMapping
    public List<Disciplin> getDisciplinas() {
        return disciplinaServicio.getDisciplinas();
    }

    @GetMapping("/menu")
    public String menu() {
        return "menudisciplina";
    }

    @GetMapping("/nueva")
    public String nuevaDisciplina() {
        return "nuevadisciplina";
    }

    @PostMapping("/registra")
    public String registra(HttpServletRequest request, Model model) {
        Disciplin nuevaDisciplina = new Disciplin(
                request.getParameter("nombre"),
                request.getParameter("clasificacion")            
            );
        disciplinaServicio.agregaDisciplina(nuevaDisciplina);
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "consultadisciplinas";
    }
    @GetMapping("/borra")
    public String borraDisciplina(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "listasdis";
    }

    @GetMapping("/borra/{id}")
    public String borraDisciplina(@PathVariable("id") Long id, Model model) {
        model.addAttribute("disciplina", disciplinaServicio.getDisciplina(id));
        return "borradisciplina";
    }
    @PostMapping("/borrar")
    public String borra(HttpServletRequest request, Model model) {
        disciplinaServicio.eliminaDisciplina(Long.parseLong(request.getParameter("id"))); 
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "listadisciplinas";
    }


    @GetMapping("/lista")
    public String consultaDisciplinas(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "consultadisciplinas";
    }


    @GetMapping("/actualiza")
    public String actualizaDisciplina(Model model) {
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "listadisciplinas";
    }

    @GetMapping("/actualiza/{id}")
    public String actualizaDisciplina(@PathVariable("id") Long id, Model model) {
        model.addAttribute("disciplina", disciplinaServicio.getDisciplina(id));
        return "actualizadisciplina";
    }

    @PostMapping("/actualizar")
    public String actualiza(HttpServletRequest request, Model model) {
        disciplinaServicio.actualizaDisciplina(Long.parseLong(request.getParameter("id")), 
                                               request.getParameter("nombre"),
                                               request.getParameter("clasificacion"));
        model.addAttribute("disciplinas", disciplinaServicio.getDisciplinas());
        return "listadisciplinas";
    }


    @PostMapping
    public void agregaDisciplinas(@RequestBody Disciplin nuevaDisciplina) {
        disciplinaServicio.agregaDisciplina(nuevaDisciplina);
    }

    // Single item

    @GetMapping(path = "{disciplinaId}")
    Disciplin disciplinaById(@PathVariable Long disciplinaId) {
        return disciplinaServicio.getDisciplina(disciplinaId);
    }

    @PutMapping(path = "{disciplinaId}")
    public void actualizaDisciplina(
        @PathVariable Long disciplinaId,
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String clasificacion) {
            disciplinaServicio.actualizaDisciplina(disciplinaId, nombre, clasificacion);
    }

    @DeleteMapping("{disciplinaId}")
    public void eliminaDisciplina(@PathVariable("disciplinaId") Long disciplinaId) {
        disciplinaServicio.eliminaDisciplina(disciplinaId);
    }
}