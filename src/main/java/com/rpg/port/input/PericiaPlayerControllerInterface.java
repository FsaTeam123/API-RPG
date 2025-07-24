package com.rpg.port.input;

import com.rpg.core.model.PericiaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PericiaPlayerControllerInterface {
    List<PericiaPlayer> listarTodos();
    ResponseEntity<PericiaPlayer> buscarPorId(Long id);
    PericiaPlayer criar(PericiaPlayer obj);
    ResponseEntity<PericiaPlayer> atualizar(Long id, PericiaPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
