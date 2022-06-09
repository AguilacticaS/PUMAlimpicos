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
@Table(name = "usuario")
public class Usuario {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Getter @Setter private Long id;
    
    @Column
    @Getter @Setter private String nombre;
    
    @Column
    @Getter @Setter private String apellidoM;
    
    @Column
    @Getter @Setter private String apellidoP;
    
    @Column
    @Getter @Setter private String email;
    
    @Column
    @Getter @Setter private String username;
    
    @Column
    @Getter @Setter private String rol;

	public Usuario(String nombre, String apellidoM, String apellidoP, String email, String username, String rol) {
		super();
		this.nombre = nombre;
		this.apellidoM = apellidoM;
		this.apellidoP = apellidoP;
		this.email = email;
		this.username = username;
		this.rol = rol;
	}	
    
}