package com.rpg.core.service;

import com.rpg.adapter.out.ClasseJogoRepository;
import com.rpg.core.model.ClasseJogo;
import com.rpg.core.model.ClasseJogoId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseJogoService {

    private final ClasseJogoRepository repository;

    public ClasseJogoService(ClasseJogoRepository repository) {
        this.repository = repository;
    }

    public List<ClasseJogo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<ClasseJogo> buscarPorId(ClasseJogoId id) {
        return repository.buscarPorId(id);
    }

    public ClasseJogo salvar(ClasseJogo obj) {
        return repository.salvar(obj);
    }

    public void deletar(ClasseJogoId id) {
        repository.deletar(id);
    }
}
