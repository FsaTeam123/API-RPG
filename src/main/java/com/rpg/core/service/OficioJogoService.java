package com.rpg.core.service;

import com.rpg.adapter.out.OficioJogoRepository;
import com.rpg.core.model.OficioJogo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OficioJogoService {

    private final OficioJogoRepository repository;

    public OficioJogoService(OficioJogoRepository repository) {
        this.repository = repository;
    }

    public List<OficioJogo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<OficioJogo> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public OficioJogo salvar(OficioJogo obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
