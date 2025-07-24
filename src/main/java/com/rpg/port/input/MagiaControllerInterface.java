package com.rpg.port.input;

import com.rpg.core.model.Magia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MagiaControllerInterface {
    List<Magia> listarTodos();
    ResponseEntity<Magia> buscarPorId(Long id);
    Magia criar(Magia magia);
    ResponseEntity<Magia> atualizar(Long id, Magia magia);
    ResponseEntity<Void> deletar(Long id);
}
