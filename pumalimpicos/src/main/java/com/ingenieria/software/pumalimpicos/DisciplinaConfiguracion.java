package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Disciplin;
import com.ingenieria.software.pumalimpicos.repositorio.DisciplinaRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaConfiguracion {
    
    @Bean
    CommandLineRunner disciplineCommandLineRunner(DisciplinaRepositorio repository) {
        return args -> {
            Disciplin atletismo = new Disciplin(
                    "Atletismo",
                    "Individuales"            
            );
            Disciplin natacion = new Disciplin(
                    "Natación",
                    "Acuáticos"            
            );
            Disciplin tenis = new Disciplin(
                    "Tenis",
                    "Con pelota"            
            );
            Disciplin boxeo = new Disciplin(
                    "Boxeo",
                    "De combate"            
            );
            Disciplin halterofilia = new Disciplin(
                    "Halterofilia",
                    "De fuerza"            
            );
            Disciplin futbol = new Disciplin(
                    "Futbol",
                    "De equipo"            
            );
            repository.saveAll(
                new ArrayList<Disciplin>() {{
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
