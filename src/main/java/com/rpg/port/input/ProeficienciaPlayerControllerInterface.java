package com.rpg.port.input;

import com.rpg.core.model.ProeficienciaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProeficienciaPlayerControllerInterface {
    List<ProeficienciaPlayer> listarTodos();
    ResponseEntity<ProeficienciaPlayer> buscarPorId(Long id);
    ProeficienciaPlayer criar(ProeficienciaPlayer obj);
    ResponseEntity<ProeficienciaPlayer> atualizar(Long id, ProeficienciaPlayer obj);
    ResponseEntity<Void> deletar(Long id);
}
