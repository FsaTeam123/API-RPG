package com.rpg.adapter.in;

import com.rpg.core.model.Itens;
import com.rpg.core.service.ItensService;
import com.rpg.port.input.ItensControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItensController implements ItensControllerInterface {

    private final ItensService service;

    public ItensController(ItensService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Itens> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Itens> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Itens criar(@RequestBody Itens obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Itens> atualizar(@PathVariable Long id, @RequestBody Itens obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdItens(id);
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
