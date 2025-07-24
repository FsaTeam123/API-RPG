package com.rpg.adapter.in;

import com.rpg.core.model.EscolaMagia;
import com.rpg.core.service.EscolaMagiaService;
import com.rpg.port.input.EscolaMagiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/escolas-magia")
public class EscolaMagiaController implements EscolaMagiaControllerInterface {

    private final EscolaMagiaService service;

    public EscolaMagiaController(EscolaMagiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<EscolaMagia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EscolaMagia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public EscolaMagia criar(@RequestBody EscolaMagia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EscolaMagia> atualizar(@PathVariable Long id, @RequestBody EscolaMagia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdEscolaMagia(id);
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
