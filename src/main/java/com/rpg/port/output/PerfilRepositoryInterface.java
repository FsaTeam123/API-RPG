package com.rpg.port.output;

import com.rpg.core.model.Perfil;
import java.util.Optional;
import java.util.List;

public interface PerfilRepositoryInterface {

    List<Perfil> listarTodos();

    Optional<Perfil> buscarPorId(Long id);

    Perfil salvar(Perfil obj);

    void deletar(Long id);
}