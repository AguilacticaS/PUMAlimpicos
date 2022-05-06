package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "disciplina")
public class Disciplina {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Long id;

    @Column
    @Getter @Setter private String nombre;

    @Column
    @Getter @Setter private String clasificacion;

    public Disciplina(String nombre, String clasificacion) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }
}