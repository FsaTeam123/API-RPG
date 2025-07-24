package com.rpg.adapter.in;

import com.rpg.core.model.CategoriaItem;
import com.rpg.core.service.CategoriaItemService;
import com.rpg.port.input.CategoriaItemControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias-item")
public class CategoriaItemController implements CategoriaItemControllerInterface {

    private final CategoriaItemService service;

    public CategoriaItemController(CategoriaItemService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<CategoriaItem> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaItem> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public CategoriaItem criar(@RequestBody CategoriaItem obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaItem> atualizar(@PathVariable Long id, @RequestBody CategoriaItem obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdCategoriaItem(id);
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
