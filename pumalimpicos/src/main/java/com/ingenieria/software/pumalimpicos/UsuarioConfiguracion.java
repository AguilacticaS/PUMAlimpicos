package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;
import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.repositorio.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class UsuarioConfiguracion {
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner userCommandLineRunner(UsuarioRepositorio repository) {
        return args -> {
            Usuario user1 = new Usuario(
                    "Diana",
                    "Patricio",
                    "Pascual",
                    "diana@gmail.com",
                    "diana",
                    "Entrenador",
                    passwordEncoder.encode("diana") 
            );

            Usuario user2 = new Usuario(
                    "Marco Antonio",
                    "Velasco",
                    "Flores",
                    "marco@gmail.com",
                    "marco",
                    "Entrenador",
                    passwordEncoder.encode("marco") 
            );

            Usuario admin = new Usuario(
                    "admin",
                    "admin",
                    "admin",
                    "admin@gmail.com",
                    "admin",
                    "Administrador",
                    passwordEncoder.encode("admin") 
            );
            
            repository.saveAll(
                new ArrayList<Usuario>() {{
                    add(user1);
                    add(user2);
                    add(admin);
                }}
            );
        };
    }
}