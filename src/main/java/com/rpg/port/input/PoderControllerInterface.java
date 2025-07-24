package com.rpg.port.input;

import com.rpg.core.model.Poder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoderControllerInterface {
    List<Poder> listarTodos();
    ResponseEntity<Poder> buscarPorId(Long id);
    Poder criar(Poder poder);
    ResponseEntity<Poder> atualizar(Long id, Poder poder);
    ResponseEntity<Void> deletar(Long id);
}
