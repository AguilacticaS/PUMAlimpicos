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
                    "Boxeo",
                     9L,
                     "Buena ejecución"   
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

            Competidor competidor3 = new Competidor(
                    "Alan",
                    "Torres",
                    "Ramírez",
                    "alan@gmail.com",
                    "alan",
                    "Competidor",
                    passwordEncoder.encode("alan"),
                    "Tenis"
            );

            Competidor competidor4 = new Competidor(
                    "Fernanda",
                    "Herrera",
                    "González",
                    "fernanda@gmail.com",
                    "fernanda",
                    "Competidor",
                    passwordEncoder.encode("fernanda"),
                    "Boxeo"
            );

            Competidor competidor5 = new Competidor(
                    "Javier",
                    "Luna",
                    "Velázquez",
                    "javier@gmail.com",
                    "javier",
                    "Competidor",
                    passwordEncoder.encode("javier"),
                    "Atletismo"
            );

            Competidor competidor6 = new Competidor(
                    "Clara",
                    "Méndez",
                    "Guerrero",
                    "clara@gmail.com",
                    "clara",
                    "Competidor",
                    passwordEncoder.encode("clara"),
                    "Natación"
            );

            Competidor competidor7 = new Competidor(
                    "María de Jesús",
                    "Ortiz",
                    "Vargas",
                    "marichuy@gmail.com",
                    "marichuy",
                    "Competidor",
                    passwordEncoder.encode("marichuy"),
                    "Atletismo"
            );

            Competidor competidor8 = new Competidor(
                    "Patricia",
                    "Reyes",
                    "Chávez",
                    "paty@gmail.com",
                    "paty",
                    "Competidor",
                    passwordEncoder.encode("paty"),
                    "Natación"
            );

            Competidor competidor9 = new Competidor(
                    "Sofia",
                    "RLópez",
                    "Cortez",
                    "sofia@gmail.com",
                    "sofia",
                    "Competidor",
                    passwordEncoder.encode("sofia"),
                    "Futbol"
            );

            Competidor competidor10 = new Competidor(
                    "José",
                    "Castillo",
                    "Guzmán",
                    "josec@gmail.com",
                    "josec",
                    "Competidor",
                    passwordEncoder.encode("josec"),
                    "Halterofilia"
            );

            repository.saveAll(
                    new ArrayList<Competidor>() {{
                        add(competidor1);
                        add(competidor2);
                        add(competidor3);
                        add(competidor4);
                        add(competidor5);
                        add(competidor6);
                        add(competidor7);
                        add(competidor8);
                        add(competidor9);
                    }}
            );
        };
    }
}
