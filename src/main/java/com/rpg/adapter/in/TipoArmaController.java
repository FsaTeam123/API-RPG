package com.rpg.adapter.in;

import com.rpg.core.model.TipoArma;
import com.rpg.core.service.TipoArmaService;
import com.rpg.port.input.TipoArmaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-arma")
public class TipoArmaController implements TipoArmaControllerInterface {

    private final TipoArmaService service;

    public TipoArmaController(TipoArmaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoArma> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoArma> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoArma criar(@RequestBody TipoArma obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoArma> atualizar(@PathVariable Long id, @RequestBody TipoArma obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoArma(id);
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
