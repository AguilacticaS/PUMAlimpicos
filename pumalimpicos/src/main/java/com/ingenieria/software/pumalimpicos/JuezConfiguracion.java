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
                    "Carmen",
                    "Reynoso",
                    "Sanchez",
                    "carmen@gmail.com",
                    "carmen",
                    "Juez",
                    passwordEncoder.encode("carmen"),
                    "Tenis"
            );

            Juez juez3 = new Juez(
                    "Valeria",
                    "Osorio",
                    "Pardo",
                    "valeria@gmail.com",
                    "valeria",
                    "Juez",
                    passwordEncoder.encode("valeria"),
                    "Atletismo"
            );

            Juez juez4 = new Juez(
                    "Joaquin",
                    "Vallejo",
                    "Magdaleno",
                    "joaquin@gmail.com",
                    "joaquin",
                    "Juez",
                    passwordEncoder.encode("joaquin"),
                    "Atletismo"
            );

            Juez juez5 = new Juez(
                    "Eleonor",
                    "Jara",
                    "Domínguez",
                    "eleonor@gmail.com",
                    "eleonor",
                    "Juez",
                    passwordEncoder.encode("eleonor"),
                    "Natación"
            );

            Juez juez6 = new Juez(
                    "Alicia",
                    "Montalvo",
                    "Vasquez",
                    "alicia@gmail.com",
                    "alicia",
                    "Juez",
                    passwordEncoder.encode("alicia"),
                    "Natación"
            );

            Juez juez7 = new Juez(
                    "Violeta",
                    "Alfaro",
                    "Castillo",
                    "violeta@gmail.com",
                    "violeta",
                    "Juez",
                    passwordEncoder.encode("violeta"),
                    "Tenis"
            );

            Juez juez8 = new Juez(
                    "Erick",
                    "Ocaña",
                    "Espino",
                    "erick@gmail.com",
                    "erick",
                    "Juez",
                    passwordEncoder.encode("erick"),
                    "Halterofilia"
            );

            Juez juez9 = new Juez(
                    "Fidel",
                    "Ordoñez",
                    "Carso",
                    "fidelk@gmail.com",
                    "fidel",
                    "Juez",
                    passwordEncoder.encode("fidel"),
                    "Halterofilia"
            );

            Juez juez10 = new Juez(
                    "Paulina",
                    "Basurto",
                    "Quevedo",
                    "paulina@gmail.com",
                    "paulina",
                    "Juez",
                    passwordEncoder.encode("paulina"),
                    "Futbol"
            );


            repository.saveAll(
                new ArrayList<Juez>() {{
                    add(juez1);
                    add(juez2);
                    add(juez3);
                    add(juez4);
                    add(juez5);
                    add(juez6);
                    add(juez7);
                    add(juez8);
                    add(juez9);
                    add(juez10);
                }}
            );
        };
    }
}
