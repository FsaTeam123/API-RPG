package com.rpg.adapter.in;

import com.rpg.core.model.Player;
import com.rpg.core.service.PlayerService;
import com.rpg.port.input.PlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController implements PlayerControllerInterface {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Player> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Player> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Player criar(@RequestBody Player player) {
        return service.salvar(player);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Player> atualizar(@PathVariable Long id, @RequestBody Player player) {
        return service.buscarPorId(id)
                .map(existing -> {
                    player.setIdPlayer(id);
                    return ResponseEntity.ok(service.salvar(player));
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
