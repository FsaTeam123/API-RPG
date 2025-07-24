package com.rpg.core.service;

import com.rpg.adapter.out.AtributoRepository;
import com.rpg.core.model.Atributo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AtributoService {

    private final AtributoRepository repository;

    public AtributoService(AtributoRepository repository) {
        this.repository = repository;
    }

    public List<Atributo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Atributo> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Atributo salvar(Atributo obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}