package com.rpg.port.input;

import com.rpg.core.model.Mapa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MapaControllerInterface {

    List<Mapa> listarTodos();

    ResponseEntity<Mapa> buscarPorId(Long id);

    Mapa criar(Mapa obj);

    ResponseEntity<Mapa> atualizar(Long id, Mapa obj);

    ResponseEntity<Void> deletar(Long id);

    // novos (opcionais na porta, se quiser manter a separação estrita)
    default ResponseEntity<Void> uploadImagem(Long id, MultipartFile file) throws IOException { return null; }
    default ResponseEntity<byte[]> getImagem(Long id) { return null; }
    default ResponseEntity<Void> deleteImagem(Long id) { return null; }
}
