package com.rpg.adapter.in;

import com.rpg.core.model.BolsaPlayer;
import com.rpg.core.service.BolsaPlayerService;
import com.rpg.port.input.BolsaPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bolsa-player")
public class BolsaPlayerController implements BolsaPlayerControllerInterface {

    private final BolsaPlayerService service;

    public BolsaPlayerController(BolsaPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<BolsaPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<BolsaPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public BolsaPlayer criar(@RequestBody BolsaPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<BolsaPlayer> atualizar(@PathVariable Long id, @RequestBody BolsaPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdBolsaPlayer(id);
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
