package com.rpg.port.output;

import com.rpg.core.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepositoryInterface {
    List<Player> listarTodos();
    Optional<Player> buscarPorId(Long id);
    Player salvar(Player player);
    void deletar(Long id);
}
