package com.rpg.adapter.in;

import com.rpg.core.model.Historia;
import com.rpg.core.service.HistoriaService;
import com.rpg.port.input.HistoriaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/historia")
public class HistoriaController implements  HistoriaControllerInterface{

    private final HistoriaService service;

    public HistoriaController(HistoriaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Historia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Historia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Historia criar(@RequestBody Historia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Historia> atualizar(@PathVariable Long id, @RequestBody Historia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdHistoria(id);
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
