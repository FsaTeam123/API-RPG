package com.rpg.port.output;

import com.rpg.core.model.PoderPlayer;

import java.util.List;
import java.util.Optional;

public interface PoderPlayerRepositoryInterface {
    List<PoderPlayer> listarTodos();
    Optional<PoderPlayer> buscarPorId(Long id);
    PoderPlayer salvar(PoderPlayer obj);
    void deletar(Long id);
}
