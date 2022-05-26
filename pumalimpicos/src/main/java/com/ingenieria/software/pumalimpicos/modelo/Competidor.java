package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

/**
 * Clase Competidor, modela la información
 * de competidor en la base de datos.
 * @author Armando Ramŕiez
 * @version
 **/
@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "competidor")
public class Competidor {

    /*Atributos*/
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column
    @Getter @Setter private String nombre;

    @Column
    @Getter @Setter private String disciplina;

    /**
     * Constructor de Competidor.
     * @param nombre Nombre del competidor.
     * @param disciplina Disciplina del competidor.
     **/
    public Competidor(String nombre, String disciplina){
        this.nombre = nombre;
        this.disciplina = disciplina;
    }
}
