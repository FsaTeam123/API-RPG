package com.rpg.port.input;

import com.rpg.core.model.PoderPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoderPlayerControllerInterface {
    List<PoderPlayer> listarTodos();
    ResponseEntity<PoderPlayer> buscarPorId(Long id);
    PoderPlayer criar(PoderPlayer obj);
    ResponseEntity<PoderPlayer> atualizar(Long id, PoderPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
