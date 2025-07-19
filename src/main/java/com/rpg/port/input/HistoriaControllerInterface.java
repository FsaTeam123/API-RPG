package com.rpg.port.input;

import com.rpg.core.model.Historia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface HistoriaControllerInterface {

    List<Historia> listarTodos();

    ResponseEntity<Historia> buscarPorId(Long id);

    Historia criar(Historia obj);

    ResponseEntity<Historia> atualizar(Long id, Historia obj);

    ResponseEntity<Void> deletar(Long id);
}
