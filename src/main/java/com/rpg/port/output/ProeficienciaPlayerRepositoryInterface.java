package com.rpg.port.output;

import com.rpg.core.model.ProeficienciaClasse;

import java.util.List;
import java.util.Optional;

public interface ProeficienciaPlayerRepositoryInterface {
    List<ProeficienciaClasse> listarTodos();
    Optional<ProeficienciaClasse> buscarPorId(Long id);
    ProeficienciaClasse salvar(ProeficienciaClasse obj);
    void deletar(Long id);
}
