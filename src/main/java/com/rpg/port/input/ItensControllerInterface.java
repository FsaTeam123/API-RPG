package com.rpg.port.input;

import com.rpg.core.model.Itens;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ItensControllerInterface {

    List<Itens> listarTodos();
    ResponseEntity<Itens> buscarPorId(Long id);
    Itens criar(Itens obj);
    ResponseEntity<Itens> atualizar(Long id, Itens obj);
    ResponseEntity<Void> deletar(Long id);
}
