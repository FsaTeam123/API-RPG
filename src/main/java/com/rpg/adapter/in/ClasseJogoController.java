package com.rpg.adapter.in;

import com.rpg.core.model.ClasseJogo;
import com.rpg.core.model.ClasseJogoId;
import com.rpg.core.service.ClasseJogoService;
import com.rpg.port.input.ClasseJogoControllerInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classe-jogo")
public class ClasseJogoController implements ClasseJogoControllerInterface {

    private final ClasseJogoService service;

    public ClasseJogoController(ClasseJogoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<ClasseJogo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @GetMapping("/buscar")
    public ResponseEntity<ClasseJogo> buscarPorId(@RequestParam Long idClasse, @RequestParam Long idJogo) {
        ClasseJogoId id = new ClasseJogoId(idClasse, idJogo);
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @PostMapping
    public ClasseJogo criar(@RequestBody ClasseJogo obj) {
        return service.salvar(obj);
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestParam Long idClasse, @RequestParam Long idJogo) {
        ClasseJogoId id = new ClasseJogoId(idClasse, idJogo);
        if (service.buscarPorId(id).isPresent()) {
            service.deletar(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
