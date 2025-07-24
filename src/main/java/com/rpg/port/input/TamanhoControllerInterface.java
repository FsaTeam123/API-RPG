package com.rpg.port.input;

import com.rpg.core.model.Tamanho;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TamanhoControllerInterface {
    List<Tamanho> listarTodos();
    ResponseEntity<Tamanho> buscarPorId(Long id);
    Tamanho criar(Tamanho obj);
    ResponseEntity<Tamanho> atualizar(Long id, Tamanho obj);
    ResponseEntity<Void> deletar(Long id);
}
