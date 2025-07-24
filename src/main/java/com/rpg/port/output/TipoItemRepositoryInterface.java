package com.rpg.port.output;

import com.rpg.core.model.TipoItem;

import java.util.List;
import java.util.Optional;

public interface TipoItemRepositoryInterface {
    List<TipoItem> listarTodos();
    Optional<TipoItem> buscarPorId(Long id);
    TipoItem salvar(TipoItem obj);
    void deletar(Long id);
}
