package com.rpg.adapter.in;

import com.rpg.core.model.Atributo;
import com.rpg.core.service.AtributoService;
import com.rpg.port.input.AtributoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atributos")
public class AtributoController implements AtributoControllerInterface {

    private final AtributoService service;

    public AtributoController(AtributoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Atributo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Atributo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Atributo criar(@RequestBody Atributo obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Atributo> atualizar(@PathVariable Long id, @RequestBody Atributo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdAtributo(id);
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