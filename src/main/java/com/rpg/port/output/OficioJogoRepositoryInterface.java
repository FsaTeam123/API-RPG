package com.rpg.port.output;

import com.rpg.core.model.OficioJogo;

import java.util.List;
import java.util.Optional;

public interface OficioJogoRepositoryInterface {
    List<OficioJogo> listarTodos();
    Optional<OficioJogo> buscarPorId(Long id);
    OficioJogo salvar(OficioJogo obj);
    void deletar(Long id);
}
