package com.rpg.port.output;

import com.rpg.core.model.EstiloCampanha;

import java.util.List;
import java.util.Optional;

public interface EstiloCampanhaRepositoryInterface {

    List<EstiloCampanha> listarTodos();

    Optional<EstiloCampanha> buscarPorId(Long id);

    EstiloCampanha salvar(EstiloCampanha obj);

    void deletar(Long id);
}
