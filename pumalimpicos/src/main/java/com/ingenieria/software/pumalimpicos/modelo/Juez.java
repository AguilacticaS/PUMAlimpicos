package com.ingenieria.software.pumalimpicos.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;


@AllArgsConstructor
@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "juez")
public class Juez extends Usuario{

	@Column @NotBlank(message="No debe ser vacío")
	private String disciplina;

    /**
     * Constructor de Juez.
     * @param nombre - Nombre del juez
     * @param disciplina - disciplina de la que es juez
     **/
	public Juez(String nombre, String apellidoP,
    String apellidoM, String email,
    String username, String rol,
    String password, String disciplina) {
		super(nombre, apellidoP,
        apellidoM, email, username, rol, password);
		this.disciplina = disciplina;
	}
}
