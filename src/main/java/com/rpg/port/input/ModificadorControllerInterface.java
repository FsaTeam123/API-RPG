package com.rpg.port.input;

import com.rpg.core.model.Modificador;
import com.rpg.core.model.ModificadorId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ModificadorControllerInterface {
    List<Modificador> listarTodos();
    ResponseEntity<Modificador> buscarPorId(Long idRaca, Long idAtributo);
    Modificador criar(Modificador obj);
    ResponseEntity<Void> deletar(Long idRaca, Long idAtributo);
}
