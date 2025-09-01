package com.rpg.port.input;

import com.rpg.adapter.in.dto.ClasseJogoCreateDTO;
import com.rpg.core.model.Classe;
import com.rpg.core.model.ClasseJogo;
import com.rpg.core.model.ClasseJogoId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ClasseJogoControllerInterface {
    List<ClasseJogo> listarTodos();
    ResponseEntity<ClasseJogo> buscarPorId(Long idClasse, Long idJogo);
    ResponseEntity<ClasseJogo> criar(ClasseJogoCreateDTO obj);
    ResponseEntity<Void> deletar(Long idClasse, Long idJogo);
    List<Classe> buscarPorIdJogo(Long idJogo);
}
