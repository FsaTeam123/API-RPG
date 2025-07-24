package com.rpg.port.input;

import com.rpg.core.model.Acao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AcaoControllerInterface {

    List<Acao> listarTodos();
    ResponseEntity<Acao> buscarPorId(Long id);
    Acao criar(Acao obj);
    ResponseEntity<Acao> atualizar(Long id, Acao obj);
    ResponseEntity<Void> deletar(Long id);
}
