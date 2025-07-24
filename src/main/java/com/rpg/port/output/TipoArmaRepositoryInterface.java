package com.rpg.port.output;

import com.rpg.core.model.TipoArma;

import java.util.List;
import java.util.Optional;

public interface TipoArmaRepositoryInterface {
    List<TipoArma> listarTodos();
    Optional<TipoArma> buscarPorId(Long id);
    TipoArma salvar(TipoArma obj);
    void deletar(Long id);
}
