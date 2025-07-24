package com.rpg.port.output;

import com.rpg.core.model.TipoPoder;

import java.util.List;
import java.util.Optional;

public interface TipoPoderRepositoryInterface {
    List<TipoPoder> listarTodos();
    Optional<TipoPoder> buscarPorId(Long id);
    TipoPoder salvar(TipoPoder obj);
    void deletar(Long id);
}
