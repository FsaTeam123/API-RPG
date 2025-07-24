package com.rpg.adapter.in;

import com.rpg.core.model.TipoPoder;
import com.rpg.core.service.TipoPoderService;
import com.rpg.port.input.TipoPoderControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-poder")
public class TipoPoderController implements TipoPoderControllerInterface {

    private final TipoPoderService service;

    public TipoPoderController(TipoPoderService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoPoder> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoPoder> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoPoder criar(@RequestBody TipoPoder obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoPoder> atualizar(@PathVariable Long id, @RequestBody TipoPoder obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoPoder(id);
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
