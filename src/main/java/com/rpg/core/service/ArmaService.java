package com.rpg.core.service;

import com.rpg.adapter.out.ArmaRepository;
import com.rpg.core.model.Arma;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmaService {

    private final ArmaRepository repository;

    public ArmaService(ArmaRepository repository) {
        this.repository = repository;
    }

    public List<Arma> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Arma> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Arma salvar(Arma obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
