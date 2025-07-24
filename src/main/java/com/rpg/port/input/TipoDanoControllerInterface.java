package com.rpg.port.input;

import com.rpg.core.model.TipoDano;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoDanoControllerInterface {
    List<TipoDano> listarTodos();
    ResponseEntity<TipoDano> buscarPorId(Long id);
    TipoDano criar(TipoDano obj);
    ResponseEntity<TipoDano> atualizar(Long id, TipoDano obj);
    ResponseEntity<Void> deletar(Long id);
}
