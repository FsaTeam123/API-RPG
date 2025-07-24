package com.rpg.port.input;

import com.rpg.core.model.Dogmas;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DogmasControllerInterface {
    List<Dogmas> listarTodos();
    ResponseEntity<Dogmas> buscarPorId(Long id);
    Dogmas criar(Dogmas obj);
    ResponseEntity<Dogmas> atualizar(Long id, Dogmas obj);
    ResponseEntity<Void> deletar(Long id);
}
