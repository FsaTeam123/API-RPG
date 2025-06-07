package com.rpg.adapter.in;

import com.rpg.core.model.Perfil;
import com.rpg.core.service.PerfilService;
import com.rpg.port.input.PerfilControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/perfils")
public class PerfilController implements PerfilControllerInterface {

    private final PerfilService service;

    public PerfilController(PerfilService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Perfil> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Perfil> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Perfil criar(@RequestBody Perfil obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Perfil> atualizar(@PathVariable Long id, @RequestBody Perfil obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdPerfil(id);
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