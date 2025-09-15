package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.MagiaPlayerCreateDTO;
import com.rpg.adapter.in.dto.MagiaPlayerResponseDTO;
import com.rpg.core.model.MagiaPlayer;
import com.rpg.core.service.MagiaPlayerService;
import com.rpg.port.input.MagiaPlayerControllerInterface;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/magia-player")
public class MagiaPlayerController implements MagiaPlayerControllerInterface {

    private final MagiaPlayerService service;

    public MagiaPlayerController(MagiaPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<MagiaPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MagiaPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagiaPlayerResponseDTO> criar(@RequestBody MagiaPlayerCreateDTO dto) {
        try {
            MagiaPlayer salvo = service.criarComIds(dto);
            MagiaPlayerResponseDTO body = new MagiaPlayerResponseDTO(
                    salvo.getIdMagiaPlayer(),
                    salvo.getPlayer().getIdPlayer(),
                    salvo.getMagia().getIdMagia()
            );
            return ResponseEntity.created(URI.create("/magia-player/" + salvo.getIdMagiaPlayer())).body(body);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build(); // 409 duplicado
        }
    }

    /** Atualiza só com IDs */
    @Override
    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MagiaPlayerResponseDTO> atualizar(@PathVariable Long id, @RequestBody MagiaPlayerCreateDTO dto) {
        try {
            MagiaPlayer atualizado = service.atualizarComIds(id, dto);
            MagiaPlayerResponseDTO body = new MagiaPlayerResponseDTO(
                    atualizado.getIdMagiaPlayer(),
                    atualizado.getPlayer().getIdPlayer(),
                    atualizado.getMagia().getIdMagia()
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
