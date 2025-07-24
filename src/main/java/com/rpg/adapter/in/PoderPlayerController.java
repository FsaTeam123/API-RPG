package com.rpg.adapter.in;

import com.rpg.core.model.PoderPlayer;
import com.rpg.core.service.PoderPlayerService;
import com.rpg.port.input.PoderPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @PostMapping
    public PoderPlayer criar(@RequestBody PoderPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<PoderPlayer> atualizar(@PathVariable Long id, @RequestBody PoderPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdPoderPlayer(id);
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
