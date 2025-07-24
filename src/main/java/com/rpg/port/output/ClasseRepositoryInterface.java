package com.rpg.port.output;

import com.rpg.core.model.Classe;

import java.util.List;
import java.util.Optional;

public interface ClasseRepositoryInterface {
    List<Classe> listarTodos();
    Optional<Classe> buscarPorId(Long id);
    Classe salvar(Classe obj);
    void deletar(Long id);
}
