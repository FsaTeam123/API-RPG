package com.rpg.port.output;

import com.rpg.core.model.Origem;

import java.util.List;
import java.util.Optional;

public interface OrigemRepositoryInterface {
    List<Origem> listarTodos();
    Optional<Origem> buscarPorId(Long id);
    Origem salvar(Origem obj);
    void deletar(Long id);
}
