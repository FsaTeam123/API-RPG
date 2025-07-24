package com.rpg.port.input;

import com.rpg.core.model.TipoArma;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoArmaControllerInterface {
    List<TipoArma> listarTodos();
    ResponseEntity<TipoArma> buscarPorId(Long id);
    TipoArma criar(TipoArma obj);
    ResponseEntity<TipoArma> atualizar(Long id, TipoArma obj);
    ResponseEntity<Void> deletar(Long id);
}
