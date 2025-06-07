package com.rpg.adapter.in;

import com.rpg.core.model.Notificacao;
import com.rpg.core.service.NotificacaoService;
import com.rpg.port.input.NotificacaoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notificacaos")
public class NotificacaoController implements NotificacaoControllerInterface {

    private final NotificacaoService service;

    public NotificacaoController(NotificacaoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Notificacao> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Notificacao> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Notificacao criar(@RequestBody Notificacao obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Notificacao> atualizar(@PathVariable Long id, @RequestBody Notificacao obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdNotificacao(id);
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
