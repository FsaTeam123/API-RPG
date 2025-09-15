package com.rpg.port.input;

import com.rpg.adapter.in.dto.PericiaPlayerCreateDTO;
import com.rpg.adapter.in.dto.PericiaPlayerResponseDTO;
import com.rpg.core.model.PericiaPlayer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PericiaPlayerControllerInterface {
    List<PericiaPlayer> listarTodos();
    ResponseEntity<PericiaPlayer> buscarPorId(Long id);
    ResponseEntity<PericiaPlayerResponseDTO> criar(PericiaPlayerCreateDTO dto);
    ResponseEntity<PericiaPlayerResponseDTO> atualizar(Long id, PericiaPlayerCreateDTO dto);
    ResponseEntity<Void> deletar(Long id);
}
