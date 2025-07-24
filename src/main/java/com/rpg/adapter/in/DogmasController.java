package com.rpg.adapter.in;

import com.rpg.core.model.Dogmas;
import com.rpg.core.service.DogmasService;
import com.rpg.port.input.DogmasControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dogmas")
public class DogmasController implements DogmasControllerInterface {

    private final DogmasService service;

    public DogmasController(DogmasService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Dogmas> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Dogmas> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Dogmas criar(@RequestBody Dogmas obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Dogmas> atualizar(@PathVariable Long id, @RequestBody Dogmas obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdDogmas(id);
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
