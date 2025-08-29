package com.rpg.port.input;

import com.rpg.core.model.ProeficienciaClasse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProeficienciaPlayerControllerInterface {
    List<ProeficienciaClasse> listarTodos();
    ResponseEntity<ProeficienciaClasse> buscarPorId(Long id);
    ProeficienciaClasse criar(ProeficienciaClasse obj);
    ResponseEntity<ProeficienciaClasse> atualizar(Long id, ProeficienciaClasse obj);
    ResponseEntity<Void> deletar(Long id);
}
