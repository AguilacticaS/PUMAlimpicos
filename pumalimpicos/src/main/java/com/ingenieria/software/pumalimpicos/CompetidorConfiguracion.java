package com.ingenieria.software.pumalimpicos;

import com.ingenieria.software.pumalimpicos.modelo.Competidor;
import com.ingenieria.software.pumalimpicos.repositorio.CompetidorRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

@Configuration
public class CompetidorConfiguracion {

    @Bean
    CommandLineRunner competidorCommandLineRunner(CompetidorRepositorio repository){
        return args -> {
            Competidor competidor1 = new Competidor(
                    "Lilith",
                    "Patricio",
                    "Pascual",
                    "dianapp@gmail.com",
                    "Dianapp",
                    "Competidor",
                    "12345dpp",
                    "atletismo"
            );
            Competidor competidor2 = new Competidor(
                    "test",
                    "test",
                    "test",
                    "test@gmail.com",
                    "test",
                    "Competidor",
                    "12345dpp",
                    "Karate"
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
