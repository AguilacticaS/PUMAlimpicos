package com.ingenieria.software.pumalimpicos.repositorio;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompetidorRepositorio extends JpaRepository<Competidor, Long>{

    @Query("SELECT j FROM Competidor j WHERE j.nombre = ?1")
    Optional<Competidor> findCompetidorByNombre(String nombre);
}
