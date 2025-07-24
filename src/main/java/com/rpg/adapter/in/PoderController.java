package com.rpg.adapter.in;

import com.rpg.core.model.Poder;
import com.rpg.core.service.PoderService;
import com.rpg.port.input.PoderControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/poderes")
public class PoderController implements PoderControllerInterface {

    private final PoderService service;

    public PoderController(PoderService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Poder> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Poder> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Poder criar(@RequestBody Poder poder) {
        return service.salvar(poder);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Poder> atualizar(@PathVariable Long id, @RequestBody Poder poder) {
        return service.buscarPorId(id)
                .map(existing -> {
                    poder.setIdPoder(id);
                    return ResponseEntity.ok(service.salvar(poder));
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
