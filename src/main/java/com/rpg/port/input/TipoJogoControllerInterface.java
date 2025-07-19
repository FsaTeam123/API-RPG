package com.rpg.port.input;

import com.rpg.core.model.TipoJogo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoJogoControllerInterface {

    List<TipoJogo> listarTodos();

    ResponseEntity<TipoJogo> buscarPorId(Long id);

    TipoJogo criar(TipoJogo obj);

    ResponseEntity<TipoJogo> atualizar(Long id, TipoJogo obj);

    ResponseEntity<Void> deletar(Long id);
}
