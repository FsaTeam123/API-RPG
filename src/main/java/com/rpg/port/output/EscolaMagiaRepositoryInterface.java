package com.rpg.port.output;

import com.rpg.core.model.EscolaMagia;

import java.util.List;
import java.util.Optional;

public interface EscolaMagiaRepositoryInterface {
    List<EscolaMagia> listarTodos();
    Optional<EscolaMagia> buscarPorId(Long id);
    EscolaMagia salvar(EscolaMagia obj);
    void deletar(Long id);
}
