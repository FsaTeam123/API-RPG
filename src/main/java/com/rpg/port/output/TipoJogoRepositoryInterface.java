package com.rpg.port.output;

import com.rpg.core.model.TipoJogo;

import java.util.List;
import java.util.Optional;

public interface TipoJogoRepositoryInterface {

    List<TipoJogo> listarTodos();

    Optional<TipoJogo> buscarPorId(Long id);

    TipoJogo salvar(TipoJogo obj);

    void deletar(Long id);
}
