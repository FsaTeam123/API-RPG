package com.rpg.port.output;

import com.rpg.core.model.TipoMagia;

import java.util.List;
import java.util.Optional;

public interface TipoMagiaRepositoryInterface {
    List<TipoMagia> listarTodos();
    Optional<TipoMagia> buscarPorId(Long id);
    TipoMagia salvar(TipoMagia obj);
    void deletar(Long id);
}
