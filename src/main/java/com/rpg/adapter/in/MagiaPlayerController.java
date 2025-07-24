package com.rpg.adapter.in;

import com.rpg.core.model.MagiaPlayer;
import com.rpg.core.service.MagiaPlayerService;
import com.rpg.port.input.MagiaPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public MagiaPlayer criar(@RequestBody MagiaPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MagiaPlayer> atualizar(@PathVariable Long id, @RequestBody MagiaPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdMagiaPlayer(id);
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
}
