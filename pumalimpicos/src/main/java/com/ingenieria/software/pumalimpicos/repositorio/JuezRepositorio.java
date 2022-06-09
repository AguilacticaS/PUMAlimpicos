package com.ingenieria.software.pumalimpicos.repositorio;

import java.util.List;
import com.ingenieria.software.pumalimpicos.modelo.Juez;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface JuezRepositorio extends JpaRepository<Juez, Long> {

    @Query("SELECT j FROM Juez j WHERE j.nombre = ?1 AND j.apellidoP = ?2 AND j.apellidoM = ?3")
    public List<Juez> findJuezByNombreYApellidos(String nombre, String apellidoPaterno, String apellidoMaterno);

    @Query("SELECT j FROM Juez j WHERE j.nombre LIKE %:nombre%")
    public List<Juez> findJuezByNombre(@Param("nombre") String nombre);

    @Query("SELECT j FROM Juez j WHERE j.apellidoP LIKE %:apellidoP%")
    public List<Juez> findJuezByApellidoPaterno(@Param("apellidoP") String apellidoP);

    @Query("SELECT j FROM Juez j WHERE j.apellidoM LIKE %:apellidoM%")
    public List<Juez> findJuezByApellidoMaterno(@Param("apellidoM") String apellidoM);

    @Query("SELECT j FROM Juez j WHERE j.nombre LIKE %:nombre% or j.apellidoP LIKE %:apellidoP%")
    public List<Juez> findJuezByNombreYApellidoPaterno(@Param("nombre") String nombre, 
                        @Param("apellidoP") String apellidoP);

    @Query("SELECT j FROM Juez j WHERE j.nombre LIKE %:nombre% or j.apellidoM LIKE %:apellidoM%")
    public List<Juez> findJuezByNombreYApellidoMaterno(@Param("nombre") String nombre,
                        @Param("apellidoM") String apellidoM);

    @Query("SELECT j FROM Juez j WHERE j.apellidoP LIKE %:apellidoP% or j.apellidoM LIKE %:apellidoM%")
    public List<Juez> findJuezByApellidoPaternoYApellidoMaterno(@Param("apellidoP") String apellidoP,
                        @Param("apellidoM") String apellidoM);

	public Juez findByUsername(String username);
}
