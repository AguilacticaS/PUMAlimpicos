package com.ingenieria.software.pumalimpicos.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "usuarios")
public class Usuario implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;

	@Column @NotBlank(message="No debe ser vacío")
	private String nombre;
	
	@Column @NotBlank(message="No debe ser vacío")
	private String apellidoP;

	@Column @NotBlank(message="No debe ser vacío")
	private String apellidoM;

	@Column @NotBlank(message="No debe ser vacío") @Email(message="Email debe ser válido")
	private String email;

	@Column @NotBlank(message="No debe ser vacío") 
	private String username;

	@Column @NotBlank(message="No debe ser vacío")
	private String rol;

	@Column @NotBlank(message="No debe ser vacío")
	private String password;

	
	public Usuario(String nombre, String apellidoP, String apellidoM, String email, String username, String rol,
			String password) {
		super();
		this.nombre = nombre;
		this.apellidoP = apellidoP;
		this.apellidoM = apellidoM;
		this.email = email;
		this.username = username;
		this.rol = rol;
		this.password = password;
	}
	
	
	public Usuario() {
		super();
	}
	
	
}