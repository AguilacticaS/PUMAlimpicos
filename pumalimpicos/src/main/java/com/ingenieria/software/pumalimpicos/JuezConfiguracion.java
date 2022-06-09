package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.repositorio.JuezRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class JuezConfiguracion {
    
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(JuezRepositorio repository) {
        return args -> {
            Juez juez1 = new Juez(
                    "Arturo Yitzac",
                    "Reynoso",
                    "Sanchez",
                    "arturo@gmail.com",
                    "arturo",
                    "Juez",
                    passwordEncoder.encode("arturo"),
                    "Boxeo"
            );
            Juez juez2 = new Juez(
                    "carmen",
                    "Reynoso",
                    "Sanchez",
                    "carmen@gmail.com",
                    "carmen",
                    "Juez",
                    passwordEncoder.encode("carmen"),
                    "Tenis"
            );
            repository.saveAll(
                new ArrayList<Juez>() {{
                    add(juez1);
                    add(juez2);
                }}
            );
        };
    }
}
