package com.rpg.port.input;

import com.rpg.core.model.EscolaMagia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EscolaMagiaControllerInterface {
    List<EscolaMagia> listarTodos();
    ResponseEntity<EscolaMagia> buscarPorId(Long id);
    EscolaMagia criar(EscolaMagia obj);
    ResponseEntity<EscolaMagia> atualizar(Long id, EscolaMagia obj);
    ResponseEntity<Void> deletar(Long id);
}
