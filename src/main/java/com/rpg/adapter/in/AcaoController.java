package com.rpg.adapter.in;

import com.rpg.core.model.Acao;
import com.rpg.core.service.AcaoService;
import com.rpg.port.input.AcaoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/acoes")
public class AcaoController implements AcaoControllerInterface {

    private final AcaoService service;

    public AcaoController(AcaoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Acao> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Acao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Acao criar(@RequestBody Acao obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Acao> atualizar(@PathVariable Long id, @RequestBody Acao obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdAcao(id);
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
