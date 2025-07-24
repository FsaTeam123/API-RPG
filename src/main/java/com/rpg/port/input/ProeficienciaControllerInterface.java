package com.rpg.port.input;

import com.rpg.core.model.Proeficiencia;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProeficienciaControllerInterface {
    List<Proeficiencia> listarTodos();
    ResponseEntity<Proeficiencia> buscarPorId(Long id);
    Proeficiencia criar(Proeficiencia obj);
    ResponseEntity<Proeficiencia> atualizar(Long id, Proeficiencia obj);
    ResponseEntity<Void> deletar(Long id);
}
