package com.rpg.port.output;

import com.rpg.core.model.ExecucaoMagia;

import java.util.List;
import java.util.Optional;

public interface ExecucaoMagiaRepositoryInterface {
    List<ExecucaoMagia> listarTodos();
    Optional<ExecucaoMagia> buscarPorId(Long id);
    ExecucaoMagia salvar(ExecucaoMagia obj);
    void deletar(Long id);
}
