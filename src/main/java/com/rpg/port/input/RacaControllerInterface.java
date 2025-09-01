package com.rpg.port.input;

import com.rpg.adapter.in.dto.RacaDTO;
import com.rpg.core.model.Raca;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RacaControllerInterface {
    List<RacaDTO> listarTodos();
    ResponseEntity<RacaDTO> buscarPorId(Long id);
    Raca criar(Raca obj);
    ResponseEntity<Raca> atualizar(Long id, Raca obj);
    ResponseEntity<Void> deletar(Long id);
}
