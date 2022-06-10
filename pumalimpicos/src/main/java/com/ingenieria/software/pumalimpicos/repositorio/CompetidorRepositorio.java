package com.ingenieria.software.pumalimpicos.repositorio;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompetidorRepositorio extends JpaRepository<Competidor, Long>{

    @Query("SELECT j FROM Competidor j WHERE j.nombre = ?1")
    Optional<Competidor> findCompetidorByNombre(String nombre);

    @Query("SELECT j FROM Competidor j WHERE j.entrenadorID = ?1")
    public List<Competidor> findByEntrenadorID(Long entrenadorID);

    @Query("SELECT j FROM Competidor j WHERE j.disciplina = ?1 ORDER BY j.calificacion DESC")
    public List<Competidor> findByDisciplina(String disciplina);

    public List<Competidor> findAllByOrderByCalificacionDesc();

	public Competidor findByUsername(String username);
}
