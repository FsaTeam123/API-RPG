package com.rpg.port.output;

import com.rpg.core.model.OrigemPericia;
import com.rpg.core.model.OrigemPericiaId;

import java.util.List;
import java.util.Optional;

public interface OrigemPericiaRepositoryInterface {
    List<OrigemPericia> listarTodos();
    Optional<OrigemPericia> buscarPorId(OrigemPericiaId id);
    OrigemPericia salvar(OrigemPericia obj);
    void deletar(OrigemPericiaId id);
}
