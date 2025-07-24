package com.rpg.adapter.in;

import com.rpg.core.model.TipoDano;
import com.rpg.core.service.TipoDanoService;
import com.rpg.port.input.TipoDanoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-dano")
public class TipoDanoController implements TipoDanoControllerInterface {

    private final TipoDanoService service;

    public TipoDanoController(TipoDanoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoDano> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoDano> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoDano criar(@RequestBody TipoDano obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoDano> atualizar(@PathVariable Long id, @RequestBody TipoDano obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoDano(id);
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
