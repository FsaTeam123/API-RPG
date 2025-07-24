package com.rpg.port.output;

import com.rpg.core.model.Proeficiencia;

import java.util.List;
import java.util.Optional;

public interface ProeficienciaRepositoryInterface {
    List<Proeficiencia> listarTodos();
    Optional<Proeficiencia> buscarPorId(Long id);
    Proeficiencia salvar(Proeficiencia obj);
    void deletar(Long id);
}
