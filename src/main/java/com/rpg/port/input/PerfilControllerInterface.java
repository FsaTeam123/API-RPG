package com.rpg.port.input;

import com.rpg.core.model.Perfil;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PerfilControllerInterface {

    List<Perfil> listarTodos();

    ResponseEntity<Perfil> buscarPorId(Long id);

    Perfil criar(Perfil obj);

    ResponseEntity<Perfil> atualizar(Long id, Perfil obj);

    ResponseEntity<Void> deletar(Long id);
}