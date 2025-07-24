package com.rpg.port.output;

import com.rpg.core.model.Pericia;

import java.util.List;
import java.util.Optional;

public interface PericiaRepositoryInterface {
    List<Pericia> listarTodos();
    Optional<Pericia> buscarPorId(Long id);
    Pericia salvar(Pericia obj);
    void deletar(Long id);
}
