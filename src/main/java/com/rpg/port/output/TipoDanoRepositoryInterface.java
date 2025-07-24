package com.rpg.port.output;

import com.rpg.core.model.TipoDano;

import java.util.List;
import java.util.Optional;

public interface TipoDanoRepositoryInterface {
    List<TipoDano> listarTodos();
    Optional<TipoDano> buscarPorId(Long id);
    TipoDano salvar(TipoDano obj);
    void deletar(Long id);
}
