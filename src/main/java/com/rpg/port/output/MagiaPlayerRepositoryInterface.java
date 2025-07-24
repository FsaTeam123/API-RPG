package com.rpg.port.output;

import com.rpg.core.model.MagiaPlayer;

import java.util.List;
import java.util.Optional;

public interface MagiaPlayerRepositoryInterface {
    List<MagiaPlayer> listarTodos();
    Optional<MagiaPlayer> buscarPorId(Long id);
    MagiaPlayer salvar(MagiaPlayer obj);
    void deletar(Long id);
}
