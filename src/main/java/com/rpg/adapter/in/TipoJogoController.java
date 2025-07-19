package com.rpg.adapter.in;

import com.rpg.core.model.TipoJogo;
import com.rpg.core.service.TipoJogoService;
import com.rpg.port.input.TipoJogoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-jogo")
public class TipoJogoController implements TipoJogoControllerInterface {

    private final TipoJogoService service;

    public TipoJogoController(TipoJogoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoJogo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoJogo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoJogo criar(@RequestBody TipoJogo obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoJogo> atualizar(@PathVariable Long id, @RequestBody TipoJogo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoJogo(id);
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
