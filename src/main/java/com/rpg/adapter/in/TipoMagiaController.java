package com.rpg.adapter.in;

import com.rpg.core.model.TipoMagia;
import com.rpg.core.service.TipoMagiaService;
import com.rpg.port.input.TipoMagiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-magia")
public class TipoMagiaController implements TipoMagiaControllerInterface {

    private final TipoMagiaService service;

    public TipoMagiaController(TipoMagiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoMagia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoMagia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoMagia criar(@RequestBody TipoMagia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoMagia> atualizar(@PathVariable Long id, @RequestBody TipoMagia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoMagia(id);
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
