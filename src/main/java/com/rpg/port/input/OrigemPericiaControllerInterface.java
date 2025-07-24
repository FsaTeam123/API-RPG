package com.rpg.port.input;

import com.rpg.core.model.OrigemPericia;
import com.rpg.core.model.OrigemPericiaId;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrigemPericiaControllerInterface {
    List<OrigemPericia> listarTodos();
    ResponseEntity<OrigemPericia> buscarPorId(Long idOrigem, Long idItens);
    OrigemPericia criar(OrigemPericia obj);
    ResponseEntity<Void> deletar(Long idOrigem, Long idItens);
}
