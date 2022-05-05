package com.ingenieria.software.pumalimpicos.controlador;


import java.util.Arrays;
import java.util.List;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.servicio.JuezServicio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping(path = "/jueces")
public class JuezControlador {

    private final JuezServicio juezServicio;

    @Autowired
    JuezControlador(JuezServicio juezServicio) {
        this.juezServicio = juezServicio;
    }

    @RequestMapping()
    public String verMenuJuez() {
        return "menujuez";
    }

    @RequestMapping("/buscarjuez")
    public String verBuscarJuez(Model model) {
        model.addAttribute("juez", new Juez());
       // List<Disciplina> enums = Arrays.asList(Disciplina.values());
      // model.addAttribute("disciplinas", enums);
        return "buscarjuez";
    }

    @GetMapping("/resultado_busqueda_juez")
    public String getJueces(){ //, @ModelAttribute("disciplina") Disciplina disciplina) {
        //model.addAttribute("disciplinas", Disciplina.values());

        //List<Juez> juecesByDisciplina =  juezServicio.getJuecesByDisciplina(disciplina);
        //model.addAttribute("juecesByDisciplina", juecesByDisciplina);
        return "resultado_busqueda_juez";
    }


    //@GetMapping
    //public String getJueces() {
      //  return "login"; 
        //juezServicio.getJueces();
    //}

    @PostMapping
    public void agregaJuez(@RequestBody Juez nuevoJuez) {
        juezServicio.agregaJuez(nuevoJuez);
    }

    // Single item

    @GetMapping(path = "{juezId}")
    Juez juezById(@PathVariable Long juezId) {
        return juezServicio.getJuez(juezId);
    }

    @PutMapping(path = "{juezId}")
    public void actualizaJuez(
        @PathVariable Long juezId,
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) Disciplina disciplina) {
            juezServicio.actualizaJuez(juezId, nombre, disciplina);
    }

    @DeleteMapping("{juezId}")
    public void eliminaJuez(@PathVariable("juezId") Long juezId) {
        juezServicio.eliminaJuez(juezId);
    }
}