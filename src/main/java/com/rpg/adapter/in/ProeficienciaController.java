package com.rpg.adapter.in;

import com.rpg.core.model.Proeficiencia;
import com.rpg.core.service.ProeficienciaService;
import com.rpg.port.input.ProeficienciaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proeficiencias")
public class ProeficienciaController implements ProeficienciaControllerInterface {

    private final ProeficienciaService service;

    public ProeficienciaController(ProeficienciaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Proeficiencia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Proeficiencia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Proeficiencia criar(@RequestBody Proeficiencia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Proeficiencia> atualizar(@PathVariable Long id, @RequestBody Proeficiencia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdProeficiencia(id);
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
