package com.rpg.port.input;

import com.rpg.core.model.Resistencia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ResistenciaControllerInterface {
    List<Resistencia> listarTodos();
    ResponseEntity<Resistencia> buscarPorId(Long id);
    Resistencia criar(Resistencia obj);
    ResponseEntity<Resistencia> atualizar(Long id, Resistencia obj);
    ResponseEntity<Void> deletar(Long id);
}
