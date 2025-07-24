package com.rpg.port.input;

import com.rpg.core.model.TipoItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TipoItemControllerInterface {

    List<TipoItem> listarTodos();
    ResponseEntity<TipoItem> buscarPorId(Long id);
    TipoItem criar(TipoItem obj);
    ResponseEntity<TipoItem> atualizar(Long id, TipoItem obj);
    ResponseEntity<Void> deletar(Long id);
}
