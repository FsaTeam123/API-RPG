package com.rpg.port.output;

import com.rpg.core.model.Acao;
import java.util.List;
import java.util.Optional;

public interface AcaoRepositoryInterface {
    List<Acao> listarTodos();
    Optional<Acao> buscarPorId(Long id);
    Acao salvar(Acao obj);
    void deletar(Long id);
}
