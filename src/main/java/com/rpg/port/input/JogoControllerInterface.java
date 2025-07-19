package com.rpg.port.input;

import com.rpg.core.model.Jogo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface JogoControllerInterface {

    List<Jogo> listarTodos();

    ResponseEntity<Jogo> buscarPorId(Long id);

    Jogo criar(Jogo obj);

    ResponseEntity<Jogo> atualizar(Long id, Jogo obj);

    ResponseEntity<Void> deletar(Long id);
}
