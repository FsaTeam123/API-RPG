package com.rpg.port.input;

import com.rpg.core.model.OficioJogo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OficioJogoControllerInterface {
    List<OficioJogo> listarTodos();
    ResponseEntity<OficioJogo> buscarPorId(Long id);
    OficioJogo criar(OficioJogo obj);
    ResponseEntity<OficioJogo> atualizar(Long id, OficioJogo obj);
    ResponseEntity<Void> deletar(Long id);
}
