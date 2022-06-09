package com.ingenieria.software.pumalimpicos.servicio;

import javax.transaction.Transactional;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.repositorio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Clase CompetidorServicio
 * @author Armando Ramírez
 * @version 1.0
 **/
@Service
public class CompetidorServicio {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

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
     * Método que obtiene la lista de competidores del repositorio
     * ordenados de forma descendente de acuerdo a su calificación.
     *
     * @return List<Competidor> - lista con los competidores ordenados.
     */
    public List<Competidor> getCompetidoresPorCalif() {
        return competidorRepositorio.findAllByOrderByCalificacionDesc();
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

    /**
     * Método que devuelve los competidores con el ID de su entrenadorc.
     * @param entrenadorID - identificador del entrenador
     * @throws IllegalStateException Si el competidor no se encuentra
     *         registrado en el sistema
     **/
    public List<Competidor> getMisCompetidores(Long entreandorId){
        return competidorRepositorio.findByEntrenadorID(entreandorId);
    }

    /**
     * Método que agrega un competidor al repositorio.
     * @param competidor El competidor a agregar.
     **/
    public void agregaCompetidor(Competidor competidor){
        String nombre = competidor.getNombre();
        Optional<Competidor> competidorOptional = competidorRepositorio.findCompetidorByNombre(nombre);
        if (competidorOptional.isPresent()) {
            throw new IllegalStateException("Ya existe el competidor con el nombre dado.");
        }
		competidor.setPassword(passwordEncoder.encode(competidor.getPassword()));
        competidorRepositorio.save(competidor);
    }

    /**
     * Método para eliminar un competidor del repositorio.
     * @param competidorId Identificador del competidor.
     **/
    public void eliminaCompetidor(Long competidorId){
            boolean exists = competidorRepositorio.existsById(competidorId);
            if (!exists){
                String advertencia = "Competidor con Id: " + competidorId + "no existe.";
                throw new IllegalStateException(advertencia);
            }
            competidorRepositorio.deleteById(competidorId);
    }

    /**
     * Método para actualizar los datos de un competidor.
     * @param  competidorId Id del competidor.
     * @param nombre Nombre del competidor.
     * @param disciplina Disciplina del competidor.
     **/
    @Transactional
    public void actualizaCompetidor(Long competidorId,
                                    String nombre,
                                    String apellidoP,
                                    String apellidoM,
                                    String email,
                                    String username,
                                    String password,
                                    String disciplina){
        String advertencia = "Competidor con Id: " + competidorId + "no existe.";
        Competidor competidor = competidorRepositorio.findById(competidorId)
                .orElseThrow(() -> new IllegalStateException(advertencia));

        boolean repeated = false;
        if (!nombre.equals("")){
            repeated = nombre.equals(competidor.getNombre());
            if (!repeated) competidor.setNombre(nombre);
        } 

        if (!apellidoP.equals("")){
            repeated = apellidoP.equals(competidor.getApellidoP());
            if (!repeated) competidor.setApellidoP(apellidoP);
        } 

        if (!apellidoM.equals("")){
            repeated = apellidoM.equals(competidor.getApellidoM());
            if (!repeated) competidor.setApellidoM(apellidoM);
        } 

        if (!email.equals("")){
            repeated = email.equals(competidor.getEmail());
            if (!repeated) competidor.setEmail(email);
        } 

        if (!username.equals("")){
            repeated = username.equals(competidor.getUsername());
            if (!repeated) competidor.setUsername(username);
        } 

        if (!password.equals("")){
            repeated = passwordEncoder.encode(password).equals(competidor.getPassword());
            if (!repeated) competidor.setPassword(passwordEncoder.encode(password));
        } 

        if (!disciplina.equals("")){
            repeated = disciplina.equals(competidor.getDisciplina());
            if (!repeated)competidor.setDisciplina(disciplina);
        } 

    }
}
