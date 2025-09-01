// src/main/java/com/rpg/adapter/in/RacaController.java
package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.HabilidadeRacaDTO;
import com.rpg.adapter.in.dto.RacaDTO;
import com.rpg.core.model.HabilidadeRaca;
import com.rpg.core.model.Raca;
import com.rpg.core.service.RacaService;
import com.rpg.port.input.RacaControllerInterface;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/racas")
public class RacaController implements RacaControllerInterface {

    private final RacaService service;

    public RacaController(RacaService service) { this.service = service; }

    // GET ALL — com foto em Base64
    @Override
    @GetMapping
    public List<RacaDTO> listarTodos() {
        return service.listarTodos().stream().map(this::toDTOWithImage).toList();
    }

    // GET BY ID — com foto em Base64
    @Override
    @GetMapping("/{id}")
    public ResponseEntity<RacaDTO> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(r -> ResponseEntity.ok(toDTOWithImage(r)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST JSON — aceita foto em Base64
    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Raca criar(@RequestBody Raca obj) {
        // Se vier Base64 no próprio objeto (se você preferir usar DTO, troque aqui)
        // Recomendo DTO para entrada também. Exemplo abaixo com DTO “na unha”:
        return service.salvar(obj);
    }

    // PUT JSON — aceita foto em Base64
    @Override
    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Raca> atualizar(@PathVariable Long id, @RequestBody Raca obj) {
        Optional<Raca> opt = service.buscarPorId(id);
        if (opt.isEmpty()) return ResponseEntity.notFound().build();

        Raca entity = opt.get();
        entity.setNome(obj.getNome());
        entity.setDescricao(obj.getDescricao());
        entity.setAtivo(obj.getAtivo());

        // se você usar DTO para receber Base64, decodifique e set:
        // entity.setFoto(Base64.getDecoder().decode(dto.getFotoBase64()));
        // entity.setFotoMime(dto.getFotoMime()); entity.setFotoNome(dto.getFotoNome()); entity.setFotoTam(...)

        return ResponseEntity.ok(service.salvar(entity));
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

    // Endpoint binário para a foto (como em ClasseController)
    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> downloadFoto(@PathVariable Long id) {
        return service.buscarPorId(id)
                .filter(r -> r.getFoto() != null && r.getFoto().length > 0)
                .map(r -> ResponseEntity.ok()
                        .contentType(parseMediaTypeSafe(r.getFotoMime()))
                        .header(HttpHeaders.CONTENT_DISPOSITION,
                                "inline; filename=\"" + (r.getFotoNome() != null ? r.getFotoNome() : "raca-"+id) + "\"")
                        .body(r.getFoto()))
                .orElse(ResponseEntity.notFound().build());
    }

    // ===== Helpers =====
    private RacaDTO toDTOWithImage(Raca r) {
        RacaDTO dto = new RacaDTO();
        dto.setIdRaca(r.getIdRaca());
        dto.setNome(r.getNome());
        dto.setDescricao(r.getDescricao());
        dto.setAtivo(r.getAtivo());
        dto.setFotoMime(r.getFotoMime());
        dto.setFotoNome(r.getFotoNome());
        dto.setFotoTam(r.getFotoTam());
        dto.setFotoAtualizadaEm(r.getFotoAtualizadaEm());

        if (r.getFoto() != null && r.getFoto().length > 0) {
            dto.setFotoBase64(Base64.getEncoder().encodeToString(r.getFoto()));
        }

        if (r.getHabilidades() != null) {
            dto.setHabilidades(
                    r.getHabilidades().stream().map(this::toDTO).toList()
            );
        }
        return dto;
    }

    private HabilidadeRacaDTO toDTO(HabilidadeRaca h) {
        HabilidadeRacaDTO d = new HabilidadeRacaDTO();
        d.setId(h.getId());
        d.setNome(h.getNome());
        d.setDescricao(h.getDescricao());
        d.setAtivo(h.getAtivo());
        return d;
    }

    private MediaType parseMediaTypeSafe(String ct) {
        try { return (ct != null ? MediaType.parseMediaType(ct) : MediaType.APPLICATION_OCTET_STREAM); }
        catch (Exception e) { return MediaType.APPLICATION_OCTET_STREAM; }
    }
}
