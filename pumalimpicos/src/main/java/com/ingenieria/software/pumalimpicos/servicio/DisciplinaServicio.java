package com.ingenieria.software.pumalimpicos.servicio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.modelo.Disciplin;
import com.ingenieria.software.pumalimpicos.repositorio.*;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaServicio {
    private final DisciplinaRepositorio disciplinaRepositorio;

    @Autowired
    public DisciplinaServicio(DisciplinaRepositorio disciplinaRepositorio) {
        this.disciplinaRepositorio = disciplinaRepositorio;
    }

    public List<Disciplin> getDisciplinas() {
        return disciplinaRepositorio.findAll();
    }

    public Disciplin getDisciplina(Long disciplinaId) {
        return disciplinaRepositorio.findById(disciplinaId)
        .orElseThrow(() -> new IllegalStateException("No hay disciplina registrada con el ID"));
    }
    
    public void agregaDisciplina(Disciplin disciplina) {
        Optional<Disciplin> disciplinaOptional = disciplinaRepositorio.findDisciplinaByNombre(disciplina.getNombre());
        if (disciplinaOptional.isPresent()) {
            throw new IllegalStateException("Ya existe esta disciplina registrada");
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
        Disciplin disciplina = disciplinaRepositorio.findById(disciplinaId)
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

