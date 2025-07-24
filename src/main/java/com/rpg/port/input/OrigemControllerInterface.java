package com.rpg.port.input;

import com.rpg.core.model.Origem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrigemControllerInterface {

    List<Origem> listarTodos();
    ResponseEntity<Origem> buscarPorId(Long id);
    Origem criar(Origem obj);
    ResponseEntity<Origem> atualizar(Long id, Origem obj);
    ResponseEntity<Void> deletar(Long id);
}
