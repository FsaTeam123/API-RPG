package com.rpg.port.input;

import com.rpg.core.model.Notificacao;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface NotificacaoControllerInterface {

    List<Notificacao> listarTodos();

    ResponseEntity<Notificacao> buscarPorId(Long id);

    Notificacao criar(Notificacao obj);

    ResponseEntity<Notificacao> atualizar(Long id, Notificacao obj);

    ResponseEntity<Void> deletar(Long id);
}
