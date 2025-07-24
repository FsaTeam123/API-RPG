package com.rpg.port.input;

import com.rpg.core.model.TipoPoder;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoPoderControllerInterface {
    List<TipoPoder> listarTodos();
    ResponseEntity<TipoPoder> buscarPorId(Long id);
    TipoPoder criar(TipoPoder obj);
    ResponseEntity<TipoPoder> atualizar(Long id, TipoPoder obj);
    ResponseEntity<Void> deletar(Long id);
}
