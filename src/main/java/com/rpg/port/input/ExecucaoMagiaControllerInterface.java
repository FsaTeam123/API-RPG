package com.rpg.port.input;

import com.rpg.core.model.ExecucaoMagia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExecucaoMagiaControllerInterface {
    List<ExecucaoMagia> listarTodos();
    ResponseEntity<ExecucaoMagia> buscarPorId(Long id);
    ExecucaoMagia criar(ExecucaoMagia obj);
    ResponseEntity<ExecucaoMagia> atualizar(Long id, ExecucaoMagia obj);
    ResponseEntity<Void> deletar(Long id);
}
