package com.rpg.port.input;

import com.rpg.core.model.Sexo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SexoControllerInterface {

    List<Sexo> listarTodos();

    ResponseEntity<Sexo> buscarPorId(Long id);

    Sexo criar(Sexo obj);

    ResponseEntity<Sexo> atualizar(Long id, Sexo obj);

    ResponseEntity<Void> deletar(Long id);
}
