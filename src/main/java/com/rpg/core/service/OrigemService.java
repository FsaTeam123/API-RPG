package com.rpg.core.service;

import com.rpg.adapter.out.OrigemRepository;
import com.rpg.core.model.Origem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrigemService {

    private final OrigemRepository repository;

    public OrigemService(OrigemRepository repository) {
        this.repository = repository;
    }

    public List<Origem> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Origem> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Origem salvar(Origem obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
