package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@ToString
@NoArgsConstructor
@Entity
@Table(name = "juez")
public class Juez {
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter 
    private Long id;

    @Column
    @Getter @Setter @NotBlank(message="No debe ser vacío") 
    private String nombre;

    @Column
    @Getter @Setter @NotBlank(message="No debe ser vacío")
    private String apellidoPaterno;

    @Column
    @Getter @Setter @NotBlank(message="No debe ser vacío") 
    private String apellidoMaterno;

    @Getter @Setter @NotBlank(message="No debe ser vacío") @Email(message="Email debe ser válido")
    private String email;
    //@Enumerated(value = EnumType.STRING)
    //@Getter @Setter private Disciplina disciplina;

    /**
     * Constructor de Juez.
     * @param nombre - Nombre del juez
     * @param disciplina - disciplina de la que es juez
     **/
    public Juez(String nombre, String apellidoPaterno, String apellidoMaterno, String email) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        //Falta implementar contraseña
    }

}
