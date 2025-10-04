package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.PericiaPlayerCreateDTO;
import com.rpg.adapter.in.dto.PericiaPlayerResponseDTO;
import com.rpg.core.model.PericiaPlayer;
import com.rpg.core.service.PericiaPlayerService;
import com.rpg.port.input.PericiaPlayerControllerInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pericia-player")
public class PericiaPlayerController implements PericiaPlayerControllerInterface {

    private final PericiaPlayerService service;

    public PericiaPlayerController(PericiaPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<PericiaPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PericiaPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PericiaPlayerResponseDTO> criar(@RequestBody PericiaPlayerCreateDTO dto) {
        try {
            PericiaPlayer salvo = service.criarComIds(dto);
            PericiaPlayerResponseDTO body = new PericiaPlayerResponseDTO(
                    salvo.getIdPericiaPlayer(),
                    salvo.getPlayer().getIdPlayer(),
                    salvo.getPericia().getIdPericia()
            );
            return ResponseEntity.created(URI.create("/pericia-player/" + salvo.getIdPericiaPlayer())).body(body);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 duplicado
        }
    }

    /** Agora atualiza apenas com IDs */
    @Override
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PericiaPlayerResponseDTO> atualizar(@PathVariable Long id, @RequestBody PericiaPlayerCreateDTO dto) {
        try {
            PericiaPlayer atualizado = service.atualizarComIds(id, dto);
            PericiaPlayerResponseDTO body = new PericiaPlayerResponseDTO(
                    atualizado.getIdPericiaPlayer(),
                    atualizado.getPlayer().getIdPlayer(),
                    atualizado.getPericia().getIdPericia()
            );
            return ResponseEntity.ok(body);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
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
