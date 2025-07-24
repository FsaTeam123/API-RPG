package com.rpg.core.service;

import com.rpg.adapter.out.OrigemItensRepository;
import com.rpg.core.model.OrigemItens;
import com.rpg.core.model.OrigemItensId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrigemItensService {

    private final OrigemItensRepository repository;

    public OrigemItensService(OrigemItensRepository repository) {
        this.repository = repository;
    }

    public List<OrigemItens> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<OrigemItens> buscarPorId(OrigemItensId id) {
        return repository.buscarPorId(id);
    }

    public OrigemItens salvar(OrigemItens obj) {
        return repository.salvar(obj);
    }

    public void deletar(OrigemItensId id) {
        repository.deletar(id);
    }
}
