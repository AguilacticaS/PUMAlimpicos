package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Juez;
import com.ingenieria.software.pumalimpicos.repositorio.JuezRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JuezConfiguracion {
    
    @Bean
    CommandLineRunner commandLineRunner(JuezRepositorio repository) {
        return args -> {
            Juez maria = new Juez(
                    "Maria",
                    "Hernandez",
                    "Perez",
                    "maria@gmail.com"
            );

            Juez alex = new Juez(
                    "Alejandro",
                    "Vega",
                    "Lima",
                    "alejandro@gmail.com"
            );
            repository.saveAll(
                new ArrayList<Juez>() {{
                    add(maria);
                    add(alex);
                }}
            );
        };
    }
}
