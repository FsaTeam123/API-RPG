package com.rpg.port.output;

import com.rpg.core.model.ProeficienciaPlayer;

import java.util.List;
import java.util.Optional;

public interface ProeficienciaPlayerRepositoryInterface {
    List<ProeficienciaPlayer> listarTodos();
    Optional<ProeficienciaPlayer> buscarPorId(Long id);
    ProeficienciaPlayer salvar(ProeficienciaPlayer obj);
    void deletar(Long id);
}
