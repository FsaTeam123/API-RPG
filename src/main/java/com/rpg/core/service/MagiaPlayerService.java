package com.rpg.core.service;

import com.rpg.adapter.out.MagiaPlayerRepository;
import com.rpg.core.model.MagiaPlayer;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagiaPlayerService {

    private final MagiaPlayerRepository repository;

    public MagiaPlayerService(MagiaPlayerRepository repository) {
        this.repository = repository;
    }

    public List<MagiaPlayer> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<MagiaPlayer> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public MagiaPlayer salvar(MagiaPlayer obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
