package com.rpg.core.service;

import com.rpg.adapter.out.ArmaPlayerRepository;
import com.rpg.core.model.ArmaPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmaPlayerService {

    private final ArmaPlayerRepository repository;

    public ArmaPlayerService(ArmaPlayerRepository repository) {
        this.repository = repository;
    }

    public List<ArmaPlayer> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<ArmaPlayer> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public ArmaPlayer salvar(ArmaPlayer obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
