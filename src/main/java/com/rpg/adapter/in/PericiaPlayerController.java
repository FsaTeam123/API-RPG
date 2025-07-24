package com.rpg.adapter.in;

import com.rpg.core.model.PericiaPlayer;
import com.rpg.core.service.PericiaPlayerService;
import com.rpg.port.input.PericiaPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping
    public PericiaPlayer criar(@RequestBody PericiaPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PericiaPlayer> atualizar(@PathVariable Long id, @RequestBody PericiaPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdPericiaPlayer(id);
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
