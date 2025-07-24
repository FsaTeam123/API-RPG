package com.rpg.core.service;

import com.rpg.adapter.out.PericiaPlayerRepository;
import com.rpg.core.model.PericiaPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PericiaPlayerService {

    private final PericiaPlayerRepository repository;

    public PericiaPlayerService(PericiaPlayerRepository repository) {
        this.repository = repository;
    }

    public List<PericiaPlayer> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<PericiaPlayer> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public PericiaPlayer salvar(PericiaPlayer obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
