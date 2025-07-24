package com.rpg.port.input;

import com.rpg.core.model.Pericia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PericiaControllerInterface {

    List<Pericia> listarTodos();
    ResponseEntity<Pericia> buscarPorId(Long id);
    Pericia criar(Pericia obj);
    ResponseEntity<Pericia> atualizar(Long id, Pericia obj);
    ResponseEntity<Void> deletar(Long id);
}
