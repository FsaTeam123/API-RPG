package com.rpg.port.input;

import com.rpg.core.model.ArmaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArmaPlayerControllerInterface {
    List<ArmaPlayer> listarTodos();
    ResponseEntity<ArmaPlayer> buscarPorId(Long id);
    ArmaPlayer criar(ArmaPlayer obj);
    ResponseEntity<ArmaPlayer> atualizar(Long id, ArmaPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
