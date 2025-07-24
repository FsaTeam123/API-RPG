package com.rpg.port.output;

import com.rpg.core.model.Dogmas;

import java.util.List;
import java.util.Optional;

public interface DogmasRepositoryInterface {
    List<Dogmas> listarTodos();
    Optional<Dogmas> buscarPorId(Long id);
    Dogmas salvar(Dogmas obj);
    void deletar(Long id);
}
