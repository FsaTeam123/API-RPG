package com.rpg.adapter.in;

import com.rpg.core.model.GeracaoMundo;
import com.rpg.core.service.GeracaoMundoService;
import com.rpg.port.input.GeracaoMundoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/geracoe-mundo")
public class GeracaoMundoController implements GeracaoMundoControllerInterface {

    private final GeracaoMundoService service;

    public GeracaoMundoController(GeracaoMundoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<GeracaoMundo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GeracaoMundo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public GeracaoMundo criar(@RequestBody GeracaoMundo obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<GeracaoMundo> atualizar(@PathVariable Long id, @RequestBody GeracaoMundo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdGeracaoMundo(id);
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
