package com.rpg.adapter.in;

import com.rpg.core.model.ArmaPlayer;
import com.rpg.core.service.ArmaPlayerService;
import com.rpg.port.input.ArmaPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arma-player")
public class ArmaPlayerController implements ArmaPlayerControllerInterface {

    private final ArmaPlayerService service;

    public ArmaPlayerController(ArmaPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<ArmaPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ArmaPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ArmaPlayer criar(@RequestBody ArmaPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ArmaPlayer> atualizar(@PathVariable Long id, @RequestBody ArmaPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdArmaPlayer(id);
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
