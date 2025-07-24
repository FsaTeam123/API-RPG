package com.rpg.core.service;

import com.rpg.adapter.out.RiquezaRepository;
import com.rpg.core.model.Riqueza;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RiquezaService {

    private final RiquezaRepository repository;

    public RiquezaService(RiquezaRepository repository) {
        this.repository = repository;
    }

    public List<Riqueza> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Riqueza> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Riqueza salvar(Riqueza obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
