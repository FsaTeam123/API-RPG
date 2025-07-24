package com.rpg.port.output;

import com.rpg.core.model.Modificador;
import com.rpg.core.model.ModificadorId;

import java.util.List;
import java.util.Optional;

public interface ModificadorRepositoryInterface {
    List<Modificador> listarTodos();
    Optional<Modificador> buscarPorId(ModificadorId id);
    Modificador salvar(Modificador obj);
    void deletar(ModificadorId id);
}
