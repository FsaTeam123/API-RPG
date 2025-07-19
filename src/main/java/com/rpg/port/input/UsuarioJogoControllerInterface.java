package com.rpg.port.input;

import com.rpg.core.model.UsuarioJogo;

import java.util.List;

public interface UsuarioJogoControllerInterface {

    List<UsuarioJogo> listarTodos();

    UsuarioJogo criar(UsuarioJogo obj);
}
