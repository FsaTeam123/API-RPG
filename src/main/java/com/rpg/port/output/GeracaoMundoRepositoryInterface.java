package com.rpg.port.output;

import com.rpg.core.model.GeracaoMundo;

import java.util.List;
import java.util.Optional;

public interface GeracaoMundoRepositoryInterface {

    List<GeracaoMundo> listarTodos();

    Optional<GeracaoMundo> buscarPorId(Long id);

    GeracaoMundo salvar(GeracaoMundo obj);

    void deletar(Long id);
}
