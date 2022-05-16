package com.ingenieria.software.pumalimpicos.servicio;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Clase CompetidorServicio
 * @author Armando Ramírez
 * @version 1.0
 **/
@Service
public class CompetidorServicio {
    /*Atributos*/
    private final CompetidorRepositorio competidorRepositorio;

    /**
     * Constructor de la clase CompetidorServicio
     * @param competidorRepositorio - un objeto de la clase competidorRepositorio
     **/
    @Autowired
    public CompetidorServicio(CompetidorRepositorio competidorRepositorio){
        this.competidorRepositorio = competidorRepositorio;
    }

    /**
     * Método que obtiene la lista de competidores del
     * repositorio.
     * @return List<Competidor> - lista com objetos de tipo competidor
     **/
    public List<Competidor> getCompetidores(){
        return competidorRepositorio.findAll();
    }

    /**
     * Método que devuelve un competidor con el Id i.
     * @param competidorId - identificador del competidor
     * @throws IllegalStateException Si el competidor no se encuentra
     *         registrado en el sistema
     **/
    public Competidor getCompetidor(Long competidorId){
        return competidorRepositorio.findById(competidorId)
        .orElseThrow(() -> new IllegalStateException("No existe el competidor"));
    }

    /***/
    public void agregaCompetidor(){}

    /***/
    public void eliminaCompetidor(){}

    /***/
    @Transactional
    public void actualizaCompetidor(){}
}
