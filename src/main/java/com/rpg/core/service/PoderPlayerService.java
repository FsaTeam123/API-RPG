package com.rpg.core.service;

import com.rpg.adapter.out.PoderPlayerRepository;
import com.rpg.core.model.PoderPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoderPlayerService {

    private final PoderPlayerRepository repository;

    public PoderPlayerService(PoderPlayerRepository repository) {
        this.repository = repository;
    }

    public List<PoderPlayer> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<PoderPlayer> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public PoderPlayer salvar(PoderPlayer obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
