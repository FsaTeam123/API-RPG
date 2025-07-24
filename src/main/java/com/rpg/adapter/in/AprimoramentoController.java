package com.rpg.adapter.in;

import com.rpg.core.model.Aprimoramento;
import com.rpg.core.service.AprimoramentoService;
import com.rpg.port.input.AprimoramentoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aprimoramentos")
public class AprimoramentoController implements AprimoramentoControllerInterface {

    private final AprimoramentoService service;

    public AprimoramentoController(AprimoramentoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Aprimoramento> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Aprimoramento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Aprimoramento criar(@RequestBody Aprimoramento aprimoramento) {
        return service.salvar(aprimoramento);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Aprimoramento> atualizar(@PathVariable Long id, @RequestBody Aprimoramento aprimoramento) {
        return service.buscarPorId(id)
                .map(existing -> {
                    aprimoramento.setIdAprimoramento(id);
                    return ResponseEntity.ok(service.salvar(aprimoramento));
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
