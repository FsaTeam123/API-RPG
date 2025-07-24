package com.rpg.core.service;

import com.rpg.adapter.out.TipoPoderRepository;
import com.rpg.core.model.TipoPoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoPoderService {

    private final TipoPoderRepository repository;

    public TipoPoderService(TipoPoderRepository repository) {
        this.repository = repository;
    }

    public List<TipoPoder> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoPoder> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoPoder salvar(TipoPoder obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
