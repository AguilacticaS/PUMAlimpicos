package com.ingenieria.software.servicio;

import com.ingenieria.software.modelo.Usuario;

public interface UsuarioServicio {
    Usuario creaUsuario(String nombre, String password);
}