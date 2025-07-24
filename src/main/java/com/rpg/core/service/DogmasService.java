package com.rpg.core.service;

import com.rpg.adapter.out.DogmasRepository;
import com.rpg.core.model.Dogmas;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogmasService {

    private final DogmasRepository repository;

    public DogmasService(DogmasRepository repository) {
        this.repository = repository;
    }

    public List<Dogmas> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Dogmas> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Dogmas salvar(Dogmas obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
