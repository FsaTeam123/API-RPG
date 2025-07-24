package com.rpg.port.output;

import com.rpg.core.model.Atributo;
import java.util.List;
import java.util.Optional;

public interface AtributoRepositoryInterface {
    List<Atributo> listarTodos();
    Optional<Atributo> buscarPorId(Long id);
    Atributo salvar(Atributo obj);
    void deletar(Long id);
}