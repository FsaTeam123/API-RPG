package com.rpg.adapter.in;

import com.rpg.core.model.OrigemPericia;
import com.rpg.core.model.OrigemPericiaId;
import com.rpg.core.service.OrigemPericiaService;
import com.rpg.port.input.OrigemPericiaControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origem-pericia")
public class OrigemPericiaController implements OrigemPericiaControllerInterface {

    private final OrigemPericiaService service;

    public OrigemPericiaController(OrigemPericiaService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<OrigemPericia> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<OrigemPericia> buscarPorId(@RequestParam Long idOrigem, @RequestParam Long idPericia) {
        OrigemPericiaId id = new OrigemPericiaId(idOrigem, idPericia);
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public OrigemPericia criar(@RequestBody OrigemPericia obj) {
        return service.salvar(obj);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam Long idOrigem, @RequestParam Long idPericia) {
        OrigemPericiaId id = new OrigemPericiaId(idOrigem, idPericia);
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
