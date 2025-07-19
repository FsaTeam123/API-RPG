package com.rpg.port.input;

import com.rpg.core.model.EstiloCampanha;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface EstiloCampanhaControllerInterface {

    List<EstiloCampanha> listarTodos();

    ResponseEntity<EstiloCampanha> buscarPorId(Long id);

    EstiloCampanha criar(EstiloCampanha obj);

    ResponseEntity<EstiloCampanha> atualizar(Long id, EstiloCampanha obj);

    ResponseEntity<Void> deletar(Long id);
}
