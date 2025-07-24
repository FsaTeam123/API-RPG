package com.rpg.port.input;

import com.rpg.core.model.TipoMagia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoMagiaControllerInterface {
    List<TipoMagia> listarTodos();
    ResponseEntity<TipoMagia> buscarPorId(Long id);
    TipoMagia criar(TipoMagia obj);
    ResponseEntity<TipoMagia> atualizar(Long id, TipoMagia obj);
    ResponseEntity<Void> deletar(Long id);
}
