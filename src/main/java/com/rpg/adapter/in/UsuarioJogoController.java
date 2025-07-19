package com.rpg.adapter.in;

import com.rpg.core.model.Jogo;
import com.rpg.core.model.UsuarioJogo;
import com.rpg.core.model.UsuarioJogoId;
import com.rpg.core.service.JogoService;
import com.rpg.core.service.UsuarioJogoService;
import com.rpg.port.input.UsuarioJogoControllerInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario-jogo")
public class UsuarioJogoController implements  UsuarioJogoControllerInterface {

    private final UsuarioJogoService service;

    public UsuarioJogoController(UsuarioJogoService service) {
        this.service = service;
    }

    @Override
    @GetMapping
    public List<UsuarioJogo> listarTodos() {
        return service.listarTodos();
    }

    @Override
    @PostMapping
    public UsuarioJogo criar(@RequestBody UsuarioJogo obj) {
        return service.salvar(obj);
    }

}
