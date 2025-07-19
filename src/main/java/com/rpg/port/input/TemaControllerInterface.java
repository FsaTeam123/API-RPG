package com.rpg.port.input;

import com.rpg.core.model.Tema;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TemaControllerInterface {
    
    List<Tema> listarTodos();

    ResponseEntity<Tema> buscarPorId(Long id);

    Tema criar(Tema obj);

    ResponseEntity<Tema> atualizar(Long id, Tema obj);

    ResponseEntity<Void> deletar(Long id);
}
