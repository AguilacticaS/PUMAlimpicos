package com.ingenieria.software.pumalimpicos.servicio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServicio {
    private final DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    public DisciplinaServicio(DisciplinaRepositorio disciplinaRepositorio) {
        this.disciplinaRepositorio = disciplinaRepositorio;
    }

    public List<Disciplina> getDisciplinas() {
        return disciplinaRepositorio.findAll();
    }

    public Disciplina getDisciplina(Long disciplinaId) {
        return disciplinaRepositorio.findById(disciplinaId)
        .orElseThrow(() -> new IllegalStateException("No hay disciplina registrada con el id."));
    }
    
    public void agregaDisciplina(Disciplina disciplina) {
        Optional<Disciplina> disciplinaOptional = disciplinaRepositorio.findDisciplinaByNombre(disciplina.getNombre());
        if (disciplinaOptional.isPresent()) {
            throw new IllegalStateException("Ya existe esta disciplina registrada con el mismo nombre.");
        }    
        disciplinaRepositorio.save(disciplina);
    }

    public void eliminaDisciplina(Long disciplinaId) {
		boolean exists = disciplinaRepositorio.existsById(disciplinaId);
		if(!exists) {
			throw new IllegalStateException(
				"Disciplina con id " + disciplinaId + "no existe.");
		}
		disciplinaRepositorio.deleteById(disciplinaId);
    }

    @Transactional
    public void actualizaDisciplina(Long disciplinaId, String nombre, String clasificacion) {
        Disciplina disciplina = disciplinaRepositorio.findById(disciplinaId)
        .orElseThrow(() -> new IllegalStateException(
			"Disciplina con id " + disciplinaId + "no existe."
        ));

        if(nombre != null &&
            !nombre.equals(disciplina.getNombre())) {
            disciplina.setNombre(nombre);
        }

		if (clasificacion != null &&
            !clasificacion.equals(disciplina.getClasificacion())) {
			disciplina.setClasificacion(clasificacion);
		}
    }
}