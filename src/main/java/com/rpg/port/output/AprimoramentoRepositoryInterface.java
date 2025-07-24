package com.rpg.port.output;

import com.rpg.core.model.Aprimoramento;

import java.util.List;
import java.util.Optional;

public interface AprimoramentoRepositoryInterface {
    List<Aprimoramento> listarTodos();
    Optional<Aprimoramento> buscarPorId(Long id);
    Aprimoramento salvar(Aprimoramento aprimoramento);
    void deletar(Long id);
}
