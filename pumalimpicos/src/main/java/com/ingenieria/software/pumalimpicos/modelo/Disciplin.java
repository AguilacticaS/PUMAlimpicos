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
@Table(name = "disciplina")
public class Disciplin {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column
    @Getter @Setter private String nombre;
    @Column
    @Getter @Setter private String clasificacion;



    public Disciplin(String nombre, String clasificacion) {
        this.nombre = nombre;
        this.clasificacion = clasificacion;
    }
}



