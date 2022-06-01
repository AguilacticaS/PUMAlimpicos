package com.ingenieria.software.pumalimpicos;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.repositorio.CompetidorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@Configuration
public class CompetidorConfiguracion {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner competidorCommandLineRunner(CompetidorRepositorio repository){
        return args -> {
            Competidor competidor1 = new Competidor(
                    "Ixchel Yanira",
                    "Velázquez",
                    "Caballero",
                    "ixchel@gmail.com",
                    "ixchel",
                    "Competidor",
                    passwordEncoder.encode("ixchel"),
                    "Boxeo"
            );
            Competidor competidor2 = new Competidor(
                    "Armando",
                    "Ramírez",
                    "González",
                    "armando@gmail.com",
                    "armando",
                    "Competidor",
                    passwordEncoder.encode("armando"),
                    "Tenis"
            );
            repository.saveAll(
                    new ArrayList<Competidor>() {{
                        add(competidor1);
                        add(competidor2);
                    }}
            );
        };
    }
}
