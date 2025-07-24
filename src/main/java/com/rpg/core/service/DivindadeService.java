package com.rpg.core.service;

import com.rpg.adapter.out.DivindadeRepository;
import com.rpg.core.model.Divindade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivindadeService {

    private final DivindadeRepository repository;

    public DivindadeService(DivindadeRepository repository) {
        this.repository = repository;
    }

    public List<Divindade> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Divindade> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Divindade salvar(Divindade obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
