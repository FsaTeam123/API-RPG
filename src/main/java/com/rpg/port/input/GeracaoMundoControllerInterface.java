package com.rpg.port.input;

import com.rpg.core.model.GeracaoMundo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GeracaoMundoControllerInterface {

    List<GeracaoMundo> listarTodos();

    ResponseEntity<GeracaoMundo> buscarPorId(Long id);

    GeracaoMundo criar(GeracaoMundo obj);

    ResponseEntity<GeracaoMundo> atualizar(Long id, GeracaoMundo obj);

    ResponseEntity<Void> deletar(Long id);
}
