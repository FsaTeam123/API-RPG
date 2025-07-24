package com.rpg.adapter.in;

import com.rpg.core.model.Modificador;
import com.rpg.core.model.ModificadorId;
import com.rpg.core.service.ModificadorService;
import com.rpg.port.input.ModificadorControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/modificadores")
public class ModificadorController implements ModificadorControllerInterface {

    private final ModificadorService service;

    public ModificadorController(ModificadorService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<Modificador> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<Modificador> buscarPorId(@RequestParam Long idRaca, @RequestParam Long idAtributo) {
        ModificadorId id = new ModificadorId(idRaca, idAtributo);
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public Modificador criar(@RequestBody Modificador obj) {
        return service.salvar(obj);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam Long idRaca, @RequestParam Long idAtributo) {
        ModificadorId id = new ModificadorId(idRaca, idAtributo);
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
