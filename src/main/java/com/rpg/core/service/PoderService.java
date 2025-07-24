package com.rpg.core.service;

import com.rpg.adapter.out.PoderRepository;
import com.rpg.core.model.Poder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PoderService {

    private final PoderRepository repository;

    public PoderService(PoderRepository repository) {
        this.repository = repository;
    }

    public List<Poder> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Poder> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Poder salvar(Poder poder) {
        return repository.salvar(poder);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
