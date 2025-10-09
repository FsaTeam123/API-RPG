package com.rpg.port.output;


import com.rpg.core.model.Anotacao;

import java.util.List;
import java.util.Optional;

public interface AnotacaoRepositoryInterface {
    List<Anotacao> listarTodos();
    Optional<Anotacao> buscarPorId(Long id);
    Anotacao salvar(Anotacao obj);
    void deletar(Long id);
    List<Anotacao> buscarPorJogoId(Long jogoId);
}
