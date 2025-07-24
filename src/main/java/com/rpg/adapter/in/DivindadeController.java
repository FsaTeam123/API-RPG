package com.rpg.adapter.in;

import com.rpg.core.model.Divindade;
import com.rpg.core.service.DivindadeService;
import com.rpg.port.input.DivindadeControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divindades")
public class DivindadeController implements DivindadeControllerInterface {

    private final DivindadeService service;

    public DivindadeController(DivindadeService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Divindade> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Divindade> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Divindade criar(@RequestBody Divindade obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Divindade> atualizar(@PathVariable Long id, @RequestBody Divindade obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdDivindade(id);
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
