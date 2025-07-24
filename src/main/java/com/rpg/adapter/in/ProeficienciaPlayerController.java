package com.rpg.adapter.in;

import com.rpg.core.model.ProeficienciaPlayer;
import com.rpg.core.service.ProeficienciaPlayerService;
import com.rpg.port.input.ProeficienciaPlayerControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proeficiencia-player")
public class ProeficienciaPlayerController implements ProeficienciaPlayerControllerInterface {

    private final ProeficienciaPlayerService service;

    public ProeficienciaPlayerController(ProeficienciaPlayerService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<ProeficienciaPlayer> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProeficienciaPlayer> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ProeficienciaPlayer criar(@RequestBody ProeficienciaPlayer obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<ProeficienciaPlayer> atualizar(@PathVariable Long id, @RequestBody ProeficienciaPlayer obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdProeficienciaPlayer(id);
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
