package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.PoderPlayerCreateDTO;
import com.rpg.adapter.in.dto.PoderPlayerResponseDTO;
import com.rpg.core.model.PoderPlayer;
import com.rpg.core.service.PoderPlayerService;
import com.rpg.port.input.PoderPlayerControllerInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/poder-player")
public class PoderPlayerController implements PoderPlayerControllerInterface {

    private final PoderPlayerService service;

    public PoderPlayerController(PoderPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<PoderPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PoderPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** NOVO: cria vínculo com IDs */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoderPlayerResponseDTO> criar(@RequestBody PoderPlayerCreateDTO dto) {
        try {
            PoderPlayer salvo = service.criarComIds(dto);
            PoderPlayerResponseDTO body = new PoderPlayerResponseDTO(
                    salvo.getIdPoderPlayer(),
                    salvo.getPlayer().getIdPlayer(),
                    salvo.getPoder().getIdPoder()
            );
            return ResponseEntity.created(URI.create("/poder-player/" + salvo.getIdPoderPlayer())).body(body);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 se já existe (UK)
        }
    }

    /** NOVO: atualiza vínculo com IDs */
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PoderPlayerResponseDTO> atualizar(@PathVariable Long id, @RequestBody PoderPlayerCreateDTO dto) {
        try {
            PoderPlayer atualizado = service.atualizarComIds(id, dto);
            PoderPlayerResponseDTO body = new PoderPlayerResponseDTO(
                    atualizado.getIdPoderPlayer(),
                    atualizado.getPlayer().getIdPlayer(),
                    atualizado.getPoder().getIdPoder()
            );
            return ResponseEntity.ok(body);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 duplicado
        }
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
}
