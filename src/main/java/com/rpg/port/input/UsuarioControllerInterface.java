package com.rpg.port.input;

import com.rpg.adapter.in.dto.UsuarioCreateDTO;
import com.rpg.adapter.out.dto.ResponseDTO;
import com.rpg.adapter.out.dto.UsuarioUpdateDTO;
import com.rpg.core.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioControllerInterface {

    List<UsuarioDTO> listarTodos();

    ResponseEntity<UsuarioDTO> buscarPorId(Long id);

    ResponseEntity<ResponseDTO<UsuarioDTO>> criar(UsuarioCreateDTO dto);

    ResponseEntity<?> atualizarPorEmail(String email, UsuarioUpdateDTO dto);

    ResponseEntity<Void> deletar(Long id);

    ResponseEntity<UsuarioDTO> buscarPorEmail(String email);
    ResponseEntity<UsuarioDTO> buscarPorEmailComReset(String email);
}
