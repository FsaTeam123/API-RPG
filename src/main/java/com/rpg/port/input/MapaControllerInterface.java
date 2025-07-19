package com.rpg.port.input;

import com.rpg.core.model.Mapa;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MapaControllerInterface {

    List<Mapa> listarTodos();

    ResponseEntity<Mapa> buscarPorId(Long id);

    Mapa criar(Mapa obj);

    ResponseEntity<Mapa> atualizar(Long id, Mapa obj);

    ResponseEntity<Void> deletar(Long id);
}
