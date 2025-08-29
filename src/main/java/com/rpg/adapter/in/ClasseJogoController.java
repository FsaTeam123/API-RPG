package com.rpg.adapter.in;

import com.rpg.adapter.in.dto.ClasseJogoCreateDTO;
import com.rpg.core.model.*;
import com.rpg.core.service.ClasseJogoService;
import com.rpg.port.input.ClasseJogoControllerInterface;
import org.springframework.http.*;
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

    // ⬇️ POST recebendo só os IDs
    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClasseJogo> criar(@RequestBody ClasseJogoCreateDTO dto) {
        if (dto.getIdClasse() == null || dto.getIdJogo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        ClasseJogoId id = new ClasseJogoId(dto.getIdClasse(), dto.getIdJogo());

        // evita duplicidade
        if (service.buscarPorId(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        // monta a entidade só com os IDs (sem carregar do banco)
        ClasseJogo cj = new ClasseJogo();
        cj.setId(id);
        cj.setClasse(new Classe(dto.getIdClasse()));
        cj.setJogo(new Jogo(dto.getIdJogo()));

        ClasseJogo salvo = service.salvar(cj);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
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
