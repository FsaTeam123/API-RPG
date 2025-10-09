package com.rpg.port.input;

import com.rpg.core.model.Anotacao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AnotacaoControllerInterface {

    List<Anotacao> listarTodos();
    ResponseEntity<Anotacao> buscarPorId(Long id);
    Anotacao criar(Anotacao obj);
    ResponseEntity<Anotacao> atualizar(Long id, Anotacao obj);
    ResponseEntity<Void> deletar(Long id);
}
