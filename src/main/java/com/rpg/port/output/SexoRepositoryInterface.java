package com.rpg.port.output;

import com.rpg.core.model.Sexo;
import java.util.Optional;
import java.util.List;

public interface SexoRepositoryInterface {

    List<Sexo> listarTodos();

    Optional<Sexo> buscarPorId(Long id);

    Sexo salvar(Sexo obj);

    void deletar(Long id);
}
