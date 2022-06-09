package com.ingenieria.software.pumalimpicos;

import java.util.ArrayList;

import com.ingenieria.software.pumalimpicos.modelo.Usuario;
import com.ingenieria.software.pumalimpicos.repositorio.UsuarioRepositorio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsuarioConfiguracion {
    
    @Bean
    CommandLineRunner consultarCommandLineRunner(UsuarioRepositorio repository) {
        return args -> {
            Usuario u1 = new Usuario(
                    "Diana",
                    "Patricio",
                    "Pascual",
                    "diana@gmail.com",
                    "dianapp",
                    "entrenador"
            );
            
            Usuario u2 = new Usuario(
            		"Maria",
                    "Patricio",
                    "Pascual",
                    "maria@gmail.com",
                    "mariapp",         
                    "competidor"
            );
           
            Usuario u3 = new Usuario(
            		"Leo",
                    "Patricio",
                    "Pascual",
                    "Leo@gmail.com",
                    "Leopp",
                    "entrenador"
            );
            
            repository.saveAll(
                new ArrayList<Usuario>() {{
                    add(u1);
                    add(u2);
                    add(u3);
                }}
            );
        };
    }
}