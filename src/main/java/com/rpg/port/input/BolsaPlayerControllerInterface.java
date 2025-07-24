package com.rpg.port.input;

import com.rpg.core.model.BolsaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BolsaPlayerControllerInterface {
    List<BolsaPlayer> listarTodos();
    ResponseEntity<BolsaPlayer> buscarPorId(Long id);
    BolsaPlayer criar(BolsaPlayer obj);
    ResponseEntity<BolsaPlayer> atualizar(Long id, BolsaPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
