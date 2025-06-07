package com.rpg.adapter.in;

import com.rpg.core.model.Sexo;
import com.rpg.core.service.SexoService;
import com.rpg.port.input.SexoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sexos")
public class SexoController implements SexoControllerInterface {

    private final SexoService service;

    public SexoController(SexoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Sexo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Sexo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Sexo criar(@RequestBody Sexo obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Sexo> atualizar(@PathVariable Long id, @RequestBody Sexo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdSexo(id);
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