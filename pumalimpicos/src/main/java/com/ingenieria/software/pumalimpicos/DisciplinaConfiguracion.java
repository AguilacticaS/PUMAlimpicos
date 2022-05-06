package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Disciplina;
import com.ingenieria.software.pumalimpicos.repositorio.DisciplinaRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DisciplinaConfiguracion {
    
    @Bean
    CommandLineRunner disciplineCommandLineRunner(DisciplinaRepositorio repository) {
        return args -> {
            Disciplina atletismo = new Disciplina(
                    "Atletismo",
                    "Individuales"            
            );
            Disciplina natacion = new Disciplina(
                    "Natación",
                    "Acuáticos"            
            );
            Disciplina tenis = new Disciplina(
                    "Tenis",
                    "Con pelota"            
            );
            Disciplina boxeo = new Disciplina(
                    "Boxeo",
                    "De combate"            
            );
            Disciplina halterofilia = new Disciplina(
                    "Halterofilia",
                    "De fuerza"            
            );
            Disciplina futbol = new Disciplina(
                    "Futbol",
                    "De equipo"            
            );
            repository.saveAll(
                new ArrayList<Disciplina>() {{
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