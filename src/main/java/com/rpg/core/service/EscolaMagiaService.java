package com.rpg.core.service;

import com.rpg.adapter.out.EscolaMagiaRepository;
import com.rpg.core.model.EscolaMagia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscolaMagiaService {

    private final EscolaMagiaRepository repository;

    public EscolaMagiaService(EscolaMagiaRepository repository) {
        this.repository = repository;
    }

    public List<EscolaMagia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<EscolaMagia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public EscolaMagia salvar(EscolaMagia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
