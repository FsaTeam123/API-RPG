package com.rpg.port.output;

import com.rpg.core.model.Tamanho;

import java.util.List;
import java.util.Optional;

public interface TamanhoRepositoryInterface {
    List<Tamanho> listarTodos();
    Optional<Tamanho> buscarPorId(Long id);
    Tamanho salvar(Tamanho obj);
    void deletar(Long id);
}
