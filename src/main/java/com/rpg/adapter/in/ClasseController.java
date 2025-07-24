package com.rpg.adapter.in;

import com.rpg.core.model.Classe;
import com.rpg.core.service.ClasseService;
import com.rpg.port.input.ClasseControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
public class ClasseController implements ClasseControllerInterface {

    private final ClasseService service;

    public ClasseController(ClasseService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Classe> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Classe> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Classe criar(@RequestBody Classe obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Classe> atualizar(@PathVariable Long id, @RequestBody Classe obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdClasse(id);
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
