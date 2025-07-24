package com.rpg.core.service;

import com.rpg.adapter.out.ClasseRepository;
import com.rpg.core.model.Classe;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    private final ClasseRepository repository;

    public ClasseService(ClasseRepository repository) {
        this.repository = repository;
    }

    public List<Classe> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Classe> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Classe salvar(Classe obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
