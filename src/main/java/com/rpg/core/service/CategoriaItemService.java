package com.rpg.core.service;

import com.rpg.adapter.out.CategoriaItemRepository;
import com.rpg.core.model.CategoriaItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaItemService {

    private final CategoriaItemRepository repository;

    public CategoriaItemService(CategoriaItemRepository repository) {
        this.repository = repository;
    }

    public List<CategoriaItem> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<CategoriaItem> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public CategoriaItem salvar(CategoriaItem obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
