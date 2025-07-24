package com.rpg.adapter.in;

import com.rpg.core.model.ExecucaoMagia;
import com.rpg.core.service.ExecucaoMagiaService;
import com.rpg.port.input.ExecucaoMagiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/execucoes-magia")
public class ExecucaoMagiaController implements ExecucaoMagiaControllerInterface {

    private final ExecucaoMagiaService service;

    public ExecucaoMagiaController(ExecucaoMagiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<ExecucaoMagia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ExecucaoMagia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ExecucaoMagia criar(@RequestBody ExecucaoMagia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ExecucaoMagia> atualizar(@PathVariable Long id, @RequestBody ExecucaoMagia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdExecucaoMagia(id);
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
