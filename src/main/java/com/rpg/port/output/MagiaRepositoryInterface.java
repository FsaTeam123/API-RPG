package com.rpg.port.output;

import com.rpg.core.model.Magia;

import java.util.List;
import java.util.Optional;

public interface MagiaRepositoryInterface {
    List<Magia> listarTodos();
    Optional<Magia> buscarPorId(Long id);
    Magia salvar(Magia magia);
    void deletar(Long id);
}
