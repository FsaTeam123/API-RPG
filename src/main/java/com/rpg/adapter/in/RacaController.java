package com.rpg.adapter.in;

import com.rpg.core.model.Raca;
import com.rpg.core.service.RacaService;
import com.rpg.port.input.RacaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/racas")
public class RacaController implements RacaControllerInterface {

    private final RacaService service;

    public RacaController(RacaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Raca> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Raca> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Raca criar(@RequestBody Raca obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Raca> atualizar(@PathVariable Long id, @RequestBody Raca obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdRaca(id);
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
