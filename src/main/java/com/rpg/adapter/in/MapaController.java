package com.rpg.adapter.in;

import com.rpg.core.model.Mapa;
import com.rpg.core.service.MapaService;
import com.rpg.port.input.MapaControllerInterface;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.rpg.adapter.in.dto.MapaSummary;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/mapas")
public class MapaController implements MapaControllerInterface {

    private final MapaService service;

    public MapaController(MapaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Mapa> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Mapa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Mapa criar(@RequestBody Mapa obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Mapa> atualizar(@PathVariable Long id, @RequestBody Mapa obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdMapa(id);
                    return ResponseEntity.ok(service.salvar(obj));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    // -------- IMAGEM --------

    // Upload / substituição da imagem
    @PostMapping(
            path = "/{id}/imagem",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> uploadImagem(@PathVariable Long id,
                                             @RequestPart("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) return ResponseEntity.badRequest().build();

        String contentType = file.getContentType();
        byte[] bytes = file.getBytes();

        boolean ok = service.atualizarImagem(id, bytes, contentType);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Download / visualização da imagem
    @GetMapping("/{id}/imagem")
    public ResponseEntity<byte[]> getImagem(@PathVariable Long id) {
        var optBytes = service.obterImagem(id);
        if (optBytes.isEmpty() || optBytes.get() == null) {
            return ResponseEntity.notFound().build();
        }
        byte[] bytes = optBytes.get();
        String ct = service.obterImagemContentType(id).orElse(MediaType.APPLICATION_OCTET_STREAM_VALUE);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ct));
        headers.setContentLength(bytes.length);
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    // Remoção da imagem (mantém o Mapa)
    @DeleteMapping("/{id}/imagem")
    public ResponseEntity<Void> deleteImagem(@PathVariable Long id) {
        boolean ok = service.removerImagem(id);
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    /** Lista só os mapas de um jogo (sem o byte[] da imagem) */
    @GetMapping("/jogo/{idJogo}")
    public List<MapaSummary> listarPorJogo(@PathVariable Long idJogo) {
        return service.listarResumoPorJogo(idJogo);
    }

}
