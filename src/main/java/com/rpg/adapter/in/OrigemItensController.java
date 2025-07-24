package com.rpg.adapter.in;

import com.rpg.core.model.OrigemItens;
import com.rpg.core.model.OrigemItensId;
import com.rpg.core.service.OrigemItensService;
import com.rpg.port.input.OrigemItensControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/origem-itens")
public class OrigemItensController implements OrigemItensControllerInterface {

    private final OrigemItensService service;

    public OrigemItensController(OrigemItensService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<OrigemItens> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<OrigemItens> buscarPorId(@RequestParam Long idOrigem, @RequestParam Long idItens) {
        OrigemItensId id = new OrigemItensId(idOrigem, idItens);
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public OrigemItens criar(@RequestBody OrigemItens obj) {
        return service.salvar(obj);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam Long idOrigem, @RequestParam Long idItens) {
        OrigemItensId id = new OrigemItensId(idOrigem, idItens);
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
