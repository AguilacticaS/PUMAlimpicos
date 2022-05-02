package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "juez")
public class Juez {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    @Getter @Setter private String nombre;
    @Column
    @Enumerated(value = EnumType.STRING)
    @Getter @Setter private Disciplina disciplina;



    public Juez(String nombre, Disciplina disciplina) {
    this.nombre = nombre;
    this.disciplina = disciplina;
    }
}


