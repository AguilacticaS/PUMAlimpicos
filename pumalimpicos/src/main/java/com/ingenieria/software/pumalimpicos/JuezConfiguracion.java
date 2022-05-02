package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
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
            Juez mariam = new Juez(
                    "Mariam",
                    Disciplina.ATLETISMO_100
            );

            Juez alex = new Juez(
                    "Alex",
                    Disciplina.NATACION_LIBRE
            );
            repository.saveAll(
                new ArrayList<Juez>() {{
                    add(mariam);
                    add(alex);
                }}
            );
        };
    }
}
