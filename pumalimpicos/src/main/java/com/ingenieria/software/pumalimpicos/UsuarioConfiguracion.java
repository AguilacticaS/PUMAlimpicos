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
    CommandLineRunner userCommandLineRunner(UsuarioRepositorio repository) {
        return args -> {
            Usuario user1 = new Usuario(
                    "Diana",
                    "Patricio",
                    "Pascual",
                    "dianapp@gmail.com",
                    "Dianapp",
                    "Competidor",
                    "12345dpp"
            );
            
            Usuario user2 = new Usuario(
                    "Jose",
                    "Cruz",
                    "Perez",
                    "josecp@gmail.com",
                    "Josecp",
                    "Entrenador",
                    "12345jcp"
            );
            
            Usuario user3 = new Usuario(
                    "Maria",
                    "Rodriguez",
                    "Morales",
                    "mariarm@gmail.com",
                    "Mariarm",
                    "Juez",
                    "12345mrm"
            );
            
            repository.saveAll(
                new ArrayList<Usuario>() {{
                    add(user1);
                    add(user2);
                    add(user3);
                    
                }}
            );
        };
    }
}