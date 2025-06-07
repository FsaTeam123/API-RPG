package com.rpg.port.output;

import com.rpg.core.model.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepositoryInterface {

    List<Usuario> listarTodos();

    Optional<Usuario> buscarPorId(Long id);

    Optional<Usuario> findByEmail(String email);

    Usuario salvar(Usuario obj);

    void deletar(Long id);
}