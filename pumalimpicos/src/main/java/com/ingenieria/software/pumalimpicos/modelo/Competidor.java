package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.*;

/**
 * Clase Competidor, modela la información
 * de competidor en la base de datos.
 * @author Armando Ramŕiez
 * @version
 **/
@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "competidor")
public class Competidor extends Usuario{

	@Column @NotBlank(message="No debe ser vacío")
	private String disciplina;

	@Column
	private String comentarios;

	@Column
	private Long calificacion;
    /**
     * Constructor de Competidor.
     * @param nombre Nombre del competidor.
     * @param disciplina Disciplina del competidor.
     **/
	public Competidor(String nombre, String apellidoP,
    String apellidoM, String email,
    String username, String rol,
    String password, String disciplina) {
		super(nombre, apellidoP,
        apellidoM, email, username, rol, password);
		this.disciplina = disciplina;
        this.comentarios = "";
        this.calificacion = 0L;
	}
}
