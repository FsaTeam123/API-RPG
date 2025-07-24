package com.rpg.port.input;

import com.rpg.core.model.CategoriaItem;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoriaItemControllerInterface {

    List<CategoriaItem> listarTodos();
    ResponseEntity<CategoriaItem> buscarPorId(Long id);
    CategoriaItem criar(CategoriaItem obj);
    ResponseEntity<CategoriaItem> atualizar(Long id, CategoriaItem obj);
    ResponseEntity<Void> deletar(Long id);
}
