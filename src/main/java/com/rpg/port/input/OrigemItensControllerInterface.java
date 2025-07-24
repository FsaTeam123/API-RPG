package com.rpg.port.input;

import com.rpg.core.model.OrigemItens;
import com.rpg.core.model.OrigemItensId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrigemItensControllerInterface {
    List<OrigemItens> listarTodos();
    ResponseEntity<OrigemItens> buscarPorId(Long idOrigem, Long idItens);
    OrigemItens criar(OrigemItens obj);
    ResponseEntity<Void> deletar(Long idOrigem, Long idItens);
}
