package com.rpg.port.output;

import com.rpg.core.model.UsuarioJogo;
import com.rpg.core.model.UsuarioJogoId;

import java.util.List;
import java.util.Optional;

public interface UsuarioJogoRepositoryInterface {

    List<UsuarioJogo> listarTodos();

    UsuarioJogo salvar(UsuarioJogo obj);
}
