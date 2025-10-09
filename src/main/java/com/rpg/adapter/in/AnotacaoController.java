package com.rpg.adapter.in;

import com.rpg.core.model.Acao;
import com.rpg.core.model.Anotacao;
import com.rpg.core.service.AnotacaoService;
import com.rpg.port.input.AnotacaoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anotacao")
public class AnotacaoController implements AnotacaoControllerInterface {

    private final AnotacaoService service;

    public AnotacaoController(AnotacaoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Anotacao> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Anotacao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/jogo/{jogoId}")
    public List<Anotacao> listarPorJogo(@PathVariable Long jogoId) {
        return service.listarPorJogoId(jogoId);
    }

    @Override
    @PostMapping
    public Anotacao criar(@RequestBody Anotacao obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Anotacao> atualizar(@PathVariable Long id, @RequestBody Anotacao obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdAnotacao(id);
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
