package com.rpg.port.input;

import com.rpg.core.model.Player;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlayerControllerInterface {
    List<Player> listarTodos();
    ResponseEntity<Player> buscarPorId(Long id);
    Player criar(Player player);
    ResponseEntity<Player> atualizar(Long id, Player player);
    ResponseEntity<Void> deletar(Long id);
}
