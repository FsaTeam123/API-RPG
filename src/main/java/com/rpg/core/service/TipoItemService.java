package com.rpg.core.service;

import com.rpg.adapter.out.TipoItemRepository;
import com.rpg.core.model.TipoItem;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoItemService {

    private final TipoItemRepository repository;

    public TipoItemService(TipoItemRepository repository) {
        this.repository = repository;
    }

    public List<TipoItem> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoItem> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoItem salvar(TipoItem obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
