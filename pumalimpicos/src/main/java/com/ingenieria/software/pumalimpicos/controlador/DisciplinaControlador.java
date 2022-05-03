package com.ingenieria.software.pumalimpicos.controlador;

import java.util.List;

import com.ingenieria.software.pumalimpicos.modelo.Disciplin;
import com.ingenieria.software.pumalimpicos.servicio.DisciplinaServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
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