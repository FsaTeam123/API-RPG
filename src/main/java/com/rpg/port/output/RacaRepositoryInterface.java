package com.rpg.port.output;

import com.rpg.core.model.Raca;

import java.util.List;
import java.util.Optional;

public interface RacaRepositoryInterface {
    List<Raca> listarTodos();
    Optional<Raca> buscarPorId(Long id);
    Raca salvar(Raca obj);
    void deletar(Long id);
}
