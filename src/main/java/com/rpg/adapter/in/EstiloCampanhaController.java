package com.rpg.adapter.in;

import com.rpg.core.model.EstiloCampanha;
import com.rpg.core.service.EstiloCampanhaService;
import com.rpg.port.input.EstiloCampanhaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
    @RequestMapping("/estilos-campanha")
public class EstiloCampanhaController implements EstiloCampanhaControllerInterface {

    private final EstiloCampanhaService service;

    public EstiloCampanhaController(EstiloCampanhaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<EstiloCampanha> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EstiloCampanha> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public EstiloCampanha criar(@RequestBody EstiloCampanha obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EstiloCampanha> atualizar(@PathVariable Long id, @RequestBody EstiloCampanha obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdEstiloCampanha(id);
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
