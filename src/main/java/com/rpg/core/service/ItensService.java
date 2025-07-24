package com.rpg.core.service;

import com.rpg.adapter.out.ItensRepository;
import com.rpg.core.model.Itens;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensService {

    private final ItensRepository repository;

    public ItensService(ItensRepository repository) {
        this.repository = repository;
    }

    public List<Itens> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Itens> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Itens salvar(Itens obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
