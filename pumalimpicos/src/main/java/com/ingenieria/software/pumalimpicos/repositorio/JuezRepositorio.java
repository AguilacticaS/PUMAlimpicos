package com.ingenieria.software.pumalimpicos.repositorio;

import java.util.Optional;
import com.ingenieria.software.pumalimpicos.modelo.Juez;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JuezRepositorio extends JpaRepository<Juez, Long> {

    @Query("SELECT j FROM Juez j WHERE j.nombre = ?1 AND j.apellidoP = ?2 AND j.apellidoM = ?3")
    Optional<Juez> findJuezByNombreYApellidos(String nombre, String apellidoPaterno, String apellidoMaterno);

    //@Query("SELECT j FROM Juez j WHERE j.disciplina = ?1")
    //List<Juez> findJuezByDisciplina(Disciplina disciplina);
}
