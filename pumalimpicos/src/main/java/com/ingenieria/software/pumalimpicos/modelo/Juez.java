package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
public class Juez {
    @Id 
    @GeneratedValue
    private Long id;
    @Column
    @Getter @Setter private String nombre;
    @Column
    @Getter @Setter private Disciplina disciplina;
}
