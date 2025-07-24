package com.rpg.port.input;

import com.rpg.core.model.MagiaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MagiaPlayerControllerInterface {
    List<MagiaPlayer> listarTodos();
    ResponseEntity<MagiaPlayer> buscarPorId(Long id);
    MagiaPlayer criar(MagiaPlayer obj);
    ResponseEntity<MagiaPlayer> atualizar(Long id, MagiaPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
