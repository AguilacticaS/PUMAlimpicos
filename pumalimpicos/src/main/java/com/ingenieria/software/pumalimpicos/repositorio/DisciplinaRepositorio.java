package com.ingenieria.software.pumalimpicos.repositorio;

import java.util.Optional;

import com.ingenieria.software.pumalimpicos.modelo.Disciplin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplin, Long> {

    @Query("SELECT j FROM Disciplin j WHERE j.nombre = ?1")
    Optional<Disciplin> findDisciplinaByNombre(String nombre);
}

