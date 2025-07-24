package com.rpg.core.service;

import com.rpg.adapter.out.BolsaPlayerRepository;
import com.rpg.core.model.BolsaPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BolsaPlayerService {

    private final BolsaPlayerRepository repository;

    public BolsaPlayerService(BolsaPlayerRepository repository) {
        this.repository = repository;
    }

    public List<BolsaPlayer> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<BolsaPlayer> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public BolsaPlayer salvar(BolsaPlayer obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
