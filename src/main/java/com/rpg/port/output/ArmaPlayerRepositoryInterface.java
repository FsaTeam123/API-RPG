package com.rpg.port.output;

import com.rpg.core.model.ArmaPlayer;

import java.util.List;
import java.util.Optional;

public interface ArmaPlayerRepositoryInterface {
    List<ArmaPlayer> listarTodos();
    Optional<ArmaPlayer> buscarPorId(Long id);
    ArmaPlayer salvar(ArmaPlayer obj);
    void deletar(Long id);
}
