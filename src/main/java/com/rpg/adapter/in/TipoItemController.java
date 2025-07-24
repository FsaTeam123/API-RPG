package com.rpg.adapter.in;

import com.rpg.core.model.TipoItem;
import com.rpg.core.service.TipoItemService;
import com.rpg.port.input.TipoItemControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-item")
public class TipoItemController implements TipoItemControllerInterface {

    private final TipoItemService service;

    public TipoItemController(TipoItemService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<TipoItem> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TipoItem> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public TipoItem criar(@RequestBody TipoItem obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<TipoItem> atualizar(@PathVariable Long id, @RequestBody TipoItem obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdTipoItem(id);
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
