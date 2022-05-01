package com.ingenieria.software.pumalimpicos.repositorio;

import java.util.Optional;

import com.ingenieria.software.pumalimpicos.modelo.Juez;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JuezRepositorio extends JpaRepository<Juez, Long> {

    @Query("SELECT j FROM juez j WHERE j.nombre = ?1")
    Optional<Juez> findJuezByNombre(String nombre);
}
