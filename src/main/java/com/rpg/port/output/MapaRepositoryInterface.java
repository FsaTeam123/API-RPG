package com.rpg.port.output;

import com.rpg.core.model.Mapa;

import java.util.List;
import java.util.Optional;

public interface MapaRepositoryInterface {

    List<Mapa> listarTodos();

    Optional<Mapa> buscarPorId(Long id);

    Mapa salvar(Mapa obj);

    void deletar(Long id);
}
