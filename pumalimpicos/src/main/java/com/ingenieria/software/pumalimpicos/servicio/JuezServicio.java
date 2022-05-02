package com.ingenieria.software.pumalimpicos.servicio;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.repositorio.*;
import com.ingenieria.software.pumalimpicos.modelo.Juez;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JuezServicio {
    private final JuezRepositorio juezRepositorio;

    @Autowired
    public JuezServicio(JuezRepositorio juezRepositorio) {
        this.juezRepositorio = juezRepositorio;
    }

    public List<Juez> getJueces() {
        return juezRepositorio.findAll();
    }

    public Juez getJuez(Long juezId) {
        return juezRepositorio.findById(juezId)
        .orElseThrow(() -> new IllegalStateException("no existe juez con el Id suministrado."));
    }
    
    public void agregaJuez(Juez juez) {
        Optional<Juez> juezOptional = juezRepositorio.findJuezByNombre(juez.getNombre());
        if (juezOptional.isPresent()) {
            throw new IllegalStateException("ya existe un juez con ese nombre");
        }    
        juezRepositorio.save(juez);
    }

    public void eliminaJuez(Long juezId) {
		boolean exists = juezRepositorio.existsById(juezId);
		if(!exists) {
			throw new IllegalStateException(
				"juez con id " + juezId + " no existe.");
		}
		juezRepositorio.deleteById(juezId);
    }

    @Transactional
    public void actualizaJuez(Long juezId, String nombre, Disciplina disciplina) {
        Juez juez = juezRepositorio.findById(juezId)
        .orElseThrow(() -> new IllegalStateException(
            "juez con id " + juezId + " no existe."
        ));

        if(nombre != null &&
            !nombre.equals(juez.getNombre())) {
            juez.setNombre(nombre);
        }

		if (disciplina != null &&
            !disciplina.equals(juez.getDisciplina())) {
			juez.setDisciplina(disciplina);
		}
    }
}
