package com.rpg.port.input;

import com.rpg.core.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioControllerInterface {

    List<Usuario> listarTodos();

    ResponseEntity<Usuario> buscarPorId(Long id);

    Usuario criar(Usuario obj);

    ResponseEntity<Usuario> atualizar(Long id, Usuario obj);

    ResponseEntity<Void> deletar(Long id);
}
