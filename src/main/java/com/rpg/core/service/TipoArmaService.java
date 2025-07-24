package com.rpg.core.service;

import com.rpg.adapter.out.TipoArmaRepository;
import com.rpg.core.model.TipoArma;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoArmaService {

    private final TipoArmaRepository repository;

    public TipoArmaService(TipoArmaRepository repository) {
        this.repository = repository;
    }

    public List<TipoArma> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoArma> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoArma salvar(TipoArma obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
