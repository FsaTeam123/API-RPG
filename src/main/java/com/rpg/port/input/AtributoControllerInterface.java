package com.rpg.port.input;

import com.rpg.core.model.Atributo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AtributoControllerInterface {

    List<Atributo> listarTodos();
    ResponseEntity<Atributo> buscarPorId(Long id);
    Atributo criar(Atributo obj);
    ResponseEntity<Atributo> atualizar(Long id, Atributo obj);
    ResponseEntity<Void> deletar(Long id);
}