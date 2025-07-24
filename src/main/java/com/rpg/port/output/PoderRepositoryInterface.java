package com.rpg.port.output;

import com.rpg.core.model.Poder;

import java.util.List;
import java.util.Optional;

public interface PoderRepositoryInterface {
    List<Poder> listarTodos();
    Optional<Poder> buscarPorId(Long id);
    Poder salvar(Poder poder);
    void deletar(Long id);
}
