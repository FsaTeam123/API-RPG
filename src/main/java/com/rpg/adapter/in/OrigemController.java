package com.rpg.adapter.in;

import com.rpg.core.model.Origem;
import com.rpg.core.service.OrigemService;
import com.rpg.port.input.OrigemControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origens")
public class OrigemController implements OrigemControllerInterface {

    private final OrigemService service;

    public OrigemController(OrigemService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Origem> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Origem> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Origem criar(@RequestBody Origem obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Origem> atualizar(@PathVariable Long id, @RequestBody Origem obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdOrigem(id);
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
