package com.ingenieria.software.pumalimpicos.repositorio;

import java.util.Optional;
import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepositorio extends JpaRepository<Disciplina, Long> {

    @Query("SELECT j FROM Disciplina j WHERE j.nombre = ?1")
    Optional<Disciplina> findDisciplinaByNombre(String nombre);
}