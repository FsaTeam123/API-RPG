package com.rpg.port.output;

import com.rpg.core.model.Itens;

import java.util.List;
import java.util.Optional;

public interface ItensRepositoryInterface {
    List<Itens> listarTodos();
    Optional<Itens> buscarPorId(Long id);
    Itens salvar(Itens obj);
    void deletar(Long id);
}
