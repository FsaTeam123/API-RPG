package com.rpg.port.input;

import com.rpg.adapter.in.dto.PoderPlayerCreateDTO;
import com.rpg.adapter.in.dto.PoderPlayerResponseDTO;
import com.rpg.core.model.PoderPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoderPlayerControllerInterface {
    List<PoderPlayer> listarTodos();
    ResponseEntity<PoderPlayer> buscarPorId(Long id);
    ResponseEntity<PoderPlayerResponseDTO> criar(PoderPlayerCreateDTO dto);
    ResponseEntity<PoderPlayerResponseDTO> atualizar(Long id, PoderPlayerCreateDTO dto);
    ResponseEntity<Void> deletar(Long id);
}
