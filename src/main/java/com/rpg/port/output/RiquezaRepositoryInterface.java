package com.rpg.port.output;

import com.rpg.core.model.Riqueza;

import java.util.List;
import java.util.Optional;

public interface RiquezaRepositoryInterface {
    List<Riqueza> listarTodos();
    Optional<Riqueza> buscarPorId(Long id);
    Riqueza salvar(Riqueza obj);
    void deletar(Long id);
}
