package com.rpg.port.input;

import com.rpg.adapter.in.dto.MagiaPlayerCreateDTO;
import com.rpg.adapter.in.dto.MagiaPlayerResponseDTO;
import com.rpg.core.model.MagiaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MagiaPlayerControllerInterface {
    List<MagiaPlayer> listarTodos();
    ResponseEntity<MagiaPlayer> buscarPorId(Long id);
    ResponseEntity<MagiaPlayerResponseDTO> criar(MagiaPlayerCreateDTO dto);
    ResponseEntity<MagiaPlayerResponseDTO> atualizar(Long id, MagiaPlayerCreateDTO dto);
    ResponseEntity<Void> deletar(Long id);
}
