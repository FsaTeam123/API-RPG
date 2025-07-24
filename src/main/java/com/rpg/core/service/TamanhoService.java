package com.rpg.core.service;

import com.rpg.adapter.out.TamanhoRepository;
import com.rpg.core.model.Tamanho;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TamanhoService {

    private final TamanhoRepository repository;

    public TamanhoService(TamanhoRepository repository) {
        this.repository = repository;
    }

    public List<Tamanho> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Tamanho> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Tamanho salvar(Tamanho obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
