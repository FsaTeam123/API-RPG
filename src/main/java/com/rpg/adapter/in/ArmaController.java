package com.rpg.adapter.in;

import com.rpg.core.model.Arma;
import com.rpg.core.service.ArmaService;
import com.rpg.port.input.ArmaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/armas")
public class ArmaController implements ArmaControllerInterface {

    private final ArmaService service;

    public ArmaController(ArmaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Arma> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Arma> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Arma criar(@RequestBody Arma obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Arma> atualizar(@PathVariable Long id, @RequestBody Arma obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdArma(id);
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
