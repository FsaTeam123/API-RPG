package com.rpg.core.service;

import com.rpg.adapter.out.TipoJogoRepository;
import com.rpg.core.model.TipoJogo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoJogoService {

    private final TipoJogoRepository repository;

    public TipoJogoService(TipoJogoRepository repository) {
        this.repository = repository;
    }

    public List<TipoJogo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoJogo> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoJogo salvar(TipoJogo obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
