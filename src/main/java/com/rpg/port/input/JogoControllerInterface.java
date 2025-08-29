package com.rpg.port.input;

import com.rpg.adapter.in.dto.JogoCreateUpdateDTO;
import com.rpg.adapter.in.dto.JogoDTO;
import com.rpg.core.model.Jogo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface JogoControllerInterface {

    List<Jogo> listarTodos();

    ResponseEntity<Jogo> buscarPorId(Long id);

    ResponseEntity<JogoDTO> criar(JogoCreateUpdateDTO obj);

    ResponseEntity<JogoDTO> atualizar(Long id, JogoCreateUpdateDTO obj);

    ResponseEntity<Void> deletar(Long id);

    ResponseEntity<List<String>> buscarPorIddeUsuarioJogador(Long id);

    ResponseEntity<List<Jogo>> buscarPorIddeUsuarioMestre(Long id);
}
