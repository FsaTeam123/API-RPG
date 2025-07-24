package com.rpg.port.input;

import com.rpg.core.model.Riqueza;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RiquezaControllerInterface {
    List<Riqueza> listarTodos();
    ResponseEntity<Riqueza> buscarPorId(Long id);
    Riqueza criar(Riqueza obj);
    ResponseEntity<Riqueza> atualizar(Long id, Riqueza obj);
    ResponseEntity<Void> deletar(Long id);
}
