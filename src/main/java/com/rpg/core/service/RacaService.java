package com.rpg.core.service;

import com.rpg.adapter.out.RacaRepository;
import com.rpg.core.model.Raca;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RacaService {

    private final RacaRepository repository;

    public RacaService(RacaRepository repository) {
        this.repository = repository;
    }

    public List<Raca> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Raca> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Raca salvar(Raca obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
