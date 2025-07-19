package com.rpg.port.output;

import com.rpg.core.model.Historia;

import java.util.List;
import java.util.Optional;

public interface HistoriaRepositoryInterface {

    List<Historia> listarTodos();

    Optional<Historia> buscarPorId(Long id);

    Historia salvar(Historia obj);

    void deletar(Long id);
}
