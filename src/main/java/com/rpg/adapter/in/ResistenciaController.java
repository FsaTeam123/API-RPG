package com.rpg.adapter.in;

import com.rpg.core.model.Resistencia;
import com.rpg.core.service.ResistenciaService;
import com.rpg.port.input.ResistenciaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resistencias")
public class ResistenciaController implements ResistenciaControllerInterface {

    private final ResistenciaService service;

    public ResistenciaController(ResistenciaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Resistencia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Resistencia> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Resistencia criar(@RequestBody Resistencia obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Resistencia> atualizar(@PathVariable Long id, @RequestBody Resistencia obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdResistencia(id);
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
