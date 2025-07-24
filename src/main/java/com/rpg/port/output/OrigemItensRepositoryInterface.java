package com.rpg.port.output;

import com.rpg.core.model.OrigemItens;
import com.rpg.core.model.OrigemItensId;

import java.util.List;
import java.util.Optional;

public interface OrigemItensRepositoryInterface {
    List<OrigemItens> listarTodos();
    Optional<OrigemItens> buscarPorId(OrigemItensId id);
    OrigemItens salvar(OrigemItens obj);
    void deletar(OrigemItensId id);
}
