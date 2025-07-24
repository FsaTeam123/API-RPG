package com.rpg.adapter.in;

import com.rpg.core.model.Riqueza;
import com.rpg.core.service.RiquezaService;
import com.rpg.port.input.RiquezaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/riquezas")
public class RiquezaController implements RiquezaControllerInterface {

    private final RiquezaService service;

    public RiquezaController(RiquezaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Riqueza> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Riqueza> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Riqueza criar(@RequestBody Riqueza obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Riqueza> atualizar(@PathVariable Long id, @RequestBody Riqueza obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdRiqueza(id);
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
