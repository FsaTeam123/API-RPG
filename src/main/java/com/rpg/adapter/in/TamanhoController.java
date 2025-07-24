package com.rpg.adapter.in;

import com.rpg.core.model.Tamanho;
import com.rpg.core.service.TamanhoService;
import com.rpg.port.input.TamanhoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tamanhos")
public class TamanhoController implements TamanhoControllerInterface {

    private final TamanhoService service;

    public TamanhoController(TamanhoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Tamanho> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Tamanho> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Tamanho criar(@RequestBody Tamanho obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Tamanho> atualizar(@PathVariable Long id, @RequestBody Tamanho obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTamanho(id);
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
