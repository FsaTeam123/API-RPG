package com.rpg.port.input;

import com.rpg.adapter.in.dto.ClasseCreateUpdateDTO;
import com.rpg.adapter.in.dto.ClasseDTO;
import com.rpg.core.model.Classe;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClasseControllerInterface {
    List<ClasseDTO> listarTodos();
    ResponseEntity<ClasseDTO> buscarPorId(Long id);
    ResponseEntity<Void> deletar(Long id);
    ResponseEntity<ClasseDTO> criarMultipart(ClasseCreateUpdateDTO dto, MultipartFile imagem);
    ResponseEntity<ClasseDTO> criarJson(ClasseCreateUpdateDTO dto);
    ResponseEntity<ClasseDTO> atualizarMultipart(Long id, ClasseCreateUpdateDTO dto, MultipartFile imagem);
    ResponseEntity<ClasseDTO> atualizarJson(Long id, ClasseCreateUpdateDTO dto);
}
