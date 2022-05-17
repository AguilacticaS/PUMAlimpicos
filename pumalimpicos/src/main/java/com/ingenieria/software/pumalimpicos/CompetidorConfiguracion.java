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
            Competidor atletismo = new Competidor(
                    "Martín Hernandez",
                    "Ateltismo"
            );
            Competidor natacion = new Competidor(
                    "Tomás Hernandez",
                    "Natacion"
            );
            Competidor tenis = new Competidor(
                    "Juan García",
                    "Tenis"
            );
            Competidor boxeo = new Competidor(
                    "Giselle Montero",
                    "Boxeo"
            );
            Competidor halterofilia = new Competidor(
                    "Yaheni Garcia",
                    "Halterofilia"
            );
            Competidor futbol = new Competidor(
                    "Oscar Muñoz",
                    "Futbol"
            );
            repository.saveAll(
                    new ArrayList<Competidor>() {{
                        add(atletismo);
                        add(natacion);
                        add(tenis);
                        add(boxeo);
                        add(halterofilia);
                        add(futbol);
                    }}
            );
        };
    }
}
