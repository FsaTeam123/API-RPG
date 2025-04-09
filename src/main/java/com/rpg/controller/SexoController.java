package com.rpg.controller;

import com.rpg.entity.Sexo;
import com.rpg.service.SexoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sexos")
public class SexoController {

    private final SexoService service;

    public SexoController(SexoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Sexo> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sexo> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sexo criar(@RequestBody Sexo obj) {
        return service.salvar(obj);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sexo> atualizar(@PathVariable Long id, @RequestBody Sexo obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdSexo(id);
                    return ResponseEntity.ok(service.salvar(obj));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}