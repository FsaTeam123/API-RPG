package com.rpg.adapter.in;

import com.rpg.core.model.Pericia;
import com.rpg.core.service.PericiaService;
import com.rpg.port.input.PericiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pericias")
public class PericiaController implements PericiaControllerInterface {

    private final PericiaService service;

    public PericiaController(PericiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Pericia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Pericia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Pericia criar(@RequestBody Pericia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Pericia> atualizar(@PathVariable Long id, @RequestBody Pericia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdPericia(id);
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
