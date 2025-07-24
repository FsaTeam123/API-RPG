package com.rpg.port.output;

import com.rpg.core.model.Divindade;

import java.util.List;
import java.util.Optional;

public interface DivindadeRepositoryInterface {
    List<Divindade> listarTodos();
    Optional<Divindade> buscarPorId(Long id);
    Divindade salvar(Divindade obj);
    void deletar(Long id);
}
