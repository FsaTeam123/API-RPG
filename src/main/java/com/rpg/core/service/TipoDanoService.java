package com.rpg.core.service;

import com.rpg.adapter.out.TipoDanoRepository;
import com.rpg.core.model.TipoDano;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoDanoService {

    private final TipoDanoRepository repository;

    public TipoDanoService(TipoDanoRepository repository) {
        this.repository = repository;
    }

    public List<TipoDano> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoDano> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoDano salvar(TipoDano obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
