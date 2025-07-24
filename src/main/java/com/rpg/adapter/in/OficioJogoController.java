package com.rpg.adapter.in;

import com.rpg.core.model.OficioJogo;
import com.rpg.core.service.OficioJogoService;
import com.rpg.port.input.OficioJogoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/oficios-jogo")
public class OficioJogoController implements OficioJogoControllerInterface {

    private final OficioJogoService service;

    public OficioJogoController(OficioJogoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<OficioJogo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<OficioJogo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public OficioJogo criar(@RequestBody OficioJogo obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<OficioJogo> atualizar(@PathVariable Long id, @RequestBody OficioJogo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdOficioJogo(id);
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
