package com.rpg.port.output;

import com.rpg.core.model.PericiaPlayer;

import java.util.List;
import java.util.Optional;

public interface PericiaPlayerRepositoryInterface {
    List<PericiaPlayer> listarTodos();
    Optional<PericiaPlayer> buscarPorId(Long id);
    PericiaPlayer salvar(PericiaPlayer obj);
    void deletar(Long id);
}
