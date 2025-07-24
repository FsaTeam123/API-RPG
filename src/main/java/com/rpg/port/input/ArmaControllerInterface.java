package com.rpg.port.input;

import com.rpg.core.model.Arma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ArmaControllerInterface {
    List<Arma> listarTodos();
    ResponseEntity<Arma> buscarPorId(Long id);
    Arma criar(Arma obj);
    ResponseEntity<Arma> atualizar(Long id, Arma obj);
    ResponseEntity<Void> deletar(Long id);
}
