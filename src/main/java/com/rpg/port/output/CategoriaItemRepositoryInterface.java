package com.rpg.port.output;

import com.rpg.core.model.CategoriaItem;

import java.util.List;
import java.util.Optional;

public interface CategoriaItemRepositoryInterface {
    List<CategoriaItem> listarTodos();
    Optional<CategoriaItem> buscarPorId(Long id);
    CategoriaItem salvar(CategoriaItem obj);
    void deletar(Long id);
}
