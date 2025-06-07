package com.rpg.port.output;

import com.rpg.core.model.Notificacao;
import java.util.Optional;
import java.util.List;

public interface NotificacaoRepositoryInterface {

    List<Notificacao> listarTodos();

    Optional<Notificacao> buscarPorId(Long id);

    Notificacao salvar(Notificacao obj);

    void deletar(Long id);
}