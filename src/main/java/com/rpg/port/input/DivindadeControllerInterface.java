package com.rpg.port.input;

import com.rpg.core.model.Divindade;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DivindadeControllerInterface {
    List<Divindade> listarTodos();
    ResponseEntity<Divindade> buscarPorId(Long id);
    Divindade criar(Divindade obj);
    ResponseEntity<Divindade> atualizar(Long id, Divindade obj);
    ResponseEntity<Void> deletar(Long id);
}
