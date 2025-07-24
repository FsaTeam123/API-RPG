package com.rpg.port.output;

import com.rpg.core.model.Arma;

import java.util.List;
import java.util.Optional;

public interface ArmaRepositoryInterface {
    List<Arma> listarTodos();
    Optional<Arma> buscarPorId(Long id);
    Arma salvar(Arma obj);
    void deletar(Long id);
}
