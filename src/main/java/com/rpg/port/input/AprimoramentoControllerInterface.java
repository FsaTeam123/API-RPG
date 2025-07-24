package com.rpg.port.input;

import com.rpg.core.model.Aprimoramento;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AprimoramentoControllerInterface {
    List<Aprimoramento> listarTodos();
    ResponseEntity<Aprimoramento> buscarPorId(Long id);
    Aprimoramento criar(Aprimoramento aprimoramento);
    ResponseEntity<Aprimoramento> atualizar(Long id, Aprimoramento aprimoramento);
    ResponseEntity<Void> deletar(Long id);
}
