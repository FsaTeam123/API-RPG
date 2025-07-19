package com.rpg.port.output;

import com.rpg.core.model.Tema;

import java.util.List;
import java.util.Optional;

public interface TemaRepositoryInterface {

    List<Tema> listarTodos();

    Optional<Tema> buscarPorId(Long id);

    Tema salvar(Tema obj);

    void deletar(Long id);
}
