package com.rpg.port.output;

import com.rpg.core.model.BolsaPlayer;

import java.util.List;
import java.util.Optional;

public interface BolsaPlayerRepositoryInterface {
    List<BolsaPlayer> listarTodos();
    Optional<BolsaPlayer> buscarPorId(Long id);
    BolsaPlayer salvar(BolsaPlayer obj);
    void deletar(Long id);
}
