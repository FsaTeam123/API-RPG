package com.rpg.adapter.in;

import com.rpg.core.model.Mapa;
import com.rpg.core.service.MapaService;
import com.rpg.port.input.MapaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mapas")
public class MapaController implements MapaControllerInterface {

    private final MapaService service;

    public MapaController(MapaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Mapa> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Mapa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Mapa criar(@RequestBody Mapa obj) {
        return service.salvar(obj);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<Mapa> atualizar(@PathVariable Long id, @RequestBody Mapa obj) {
        return service.buscarPorId(id)
                .map(existing -> {
                    obj.setIdMapa(id);
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
