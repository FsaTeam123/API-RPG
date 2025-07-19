package com.rpg.core.service;

import com.rpg.adapter.out.TemaRepository;
import com.rpg.core.model.Tema;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemaService {

    private final TemaRepository repository;

    public TemaService(TemaRepository repository) {
        this.repository = repository;
    }

    public List<Tema> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Tema> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Tema salvar(Tema obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
