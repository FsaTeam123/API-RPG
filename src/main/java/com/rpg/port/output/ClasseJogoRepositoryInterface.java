package com.rpg.port.output;

import com.rpg.core.model.Classe;
import com.rpg.core.model.ClasseJogo;
import com.rpg.core.model.ClasseJogoId;

import java.util.List;
import java.util.Optional;

public interface ClasseJogoRepositoryInterface {
    List<ClasseJogo> listarTodos();
    Optional<ClasseJogo> buscarPorId(ClasseJogoId id);
    ClasseJogo salvar(ClasseJogo obj);
    void deletar(ClasseJogoId id);

    List<Classe> buscarClassePorIdJogo(Long idJogo);
}
