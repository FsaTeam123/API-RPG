package com.rpg.port.input;

import com.rpg.core.model.Classe;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClasseControllerInterface {
    List<Classe> listarTodos();
    ResponseEntity<Classe> buscarPorId(Long id);
    Classe criar(Classe obj);
    ResponseEntity<Classe> atualizar(Long id, Classe obj);
    ResponseEntity<Void> deletar(Long id);
}
