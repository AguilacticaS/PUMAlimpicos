package com.ingenieria.software.pumalimpicos.controlador;

import java.util.List;
import java.util.Optional;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.servicio.JuezServicio;

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
@RequestMapping(path = "/jueces")
public class JuezControlador {

    private final JuezServicio juezServicio;

    @Autowired
    JuezControlador(JuezServicio juezServicio) {
        this.juezServicio = juezServicio;
    }

    @GetMapping
    public List<Juez> getJueces() {
        return juezServicio.getJueces();
    }

    @PostMapping
    public void agregaJuez(@RequestBody Juez nuevoJuez) {
        juezServicio.agregaJuez(nuevoJuez);;
    }

    // Single item

    @GetMapping(path = "{juezId}")
    Optional<Juez> juezById(@PathVariable Long juezId) {
        return juezServicio.getJuez(juezId);
    }

    @PutMapping(path = "{juezId}")
    public void actualizaJuez(
        @PathVariable("juezId") Long juezId,
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) Disciplina disciplina) {
            juezServicio.actualizaJuez(juezId, nombre, disciplina);
    }

    @DeleteMapping("{juezId}")
    public void eliminaJuez(@PathVariable("juezId") Long juezId) {
        juezServicio.eliminaJuez(juezId);
    }
}