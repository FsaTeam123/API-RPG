package com.rpg.core.service;

import com.rpg.adapter.out.JogoRepository;
import com.rpg.core.model.Jogo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Jogo> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Jogo salvar(Jogo obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }

    public List<Jogo> buscarPorIdMestre(Long id) {
        return repository.buscarPorMestre(id);
    }

    public List<String> buscarPorIdJogador(Long id) {
        return repository.buscarPorJogador(id);
    }
}

