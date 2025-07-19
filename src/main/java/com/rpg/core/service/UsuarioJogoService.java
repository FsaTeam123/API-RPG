package com.rpg.core.service;

import com.rpg.adapter.out.JogoRepository;
import com.rpg.adapter.out.UsuarioJogoRepository;
import com.rpg.core.model.Jogo;
import com.rpg.core.model.UsuarioJogo;
import com.rpg.core.model.UsuarioJogoId;
import com.rpg.port.input.UsuarioJogoControllerInterface;
import com.rpg.port.output.UsuarioJogoRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioJogoService {

    private final UsuarioJogoRepository repository;

    public UsuarioJogoService(UsuarioJogoRepository repository) {
        this.repository = repository;
    }

    public List<UsuarioJogo> listarTodos() {
        return repository.listarTodos();
    }

    public UsuarioJogo salvar(UsuarioJogo obj) {
        return repository.salvar(obj);
    }
}
