package com.rpg.adapter.in;

import com.rpg.core.model.Magia;
import com.rpg.core.service.MagiaService;
import com.rpg.port.input.MagiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magias")
public class MagiaController implements MagiaControllerInterface {

    private final MagiaService service;

    public MagiaController(MagiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Magia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Magia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Magia criar(@RequestBody Magia magia) {
        return service.salvar(magia);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Magia> atualizar(@PathVariable Long id, @RequestBody Magia magia) {
        return service.buscarPorId(id)
                .map(existing -> {
                    magia.setIdMagia(id);
                    return ResponseEntity.ok(service.salvar(magia));
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
