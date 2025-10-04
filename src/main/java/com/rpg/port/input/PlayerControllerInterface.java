package com.rpg.port.input;

import com.rpg.adapter.in.dto.PlayerCreateDTO;
import com.rpg.adapter.in.dto.PlayerResponseDTO;
import com.rpg.core.model.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlayerControllerInterface {

    List<Player> listarTodos();

    Player buscarPorId(Long id);

    ResponseEntity<PlayerResponseDTO> criar(PlayerCreateDTO dto);

    ResponseEntity<Player> atualizar(Long id, Player player);

    ResponseEntity<Void> deletar(Long id);

    List<Player> listarPorJogo(Long idJogo);

    // Foto
    ResponseEntity<Void> uploadFoto(Long id, MultipartFile file) throws IOException;
    ResponseEntity<byte[]> baixarFoto(Long id);
    ResponseEntity<Void> apagarFoto(Long id);
}
