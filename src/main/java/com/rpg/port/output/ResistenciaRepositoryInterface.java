package com.rpg.port.output;

import com.rpg.core.model.Resistencia;

import java.util.List;
import java.util.Optional;

public interface ResistenciaRepositoryInterface {
    List<Resistencia> listarTodos();
    Optional<Resistencia> buscarPorId(Long id);
    Resistencia salvar(Resistencia obj);
    void deletar(Long id);
}
