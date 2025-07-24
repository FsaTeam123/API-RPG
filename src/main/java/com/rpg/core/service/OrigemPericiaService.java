package com.rpg.core.service;

import com.rpg.adapter.out.OrigemPericiaRepository;
import com.rpg.core.model.OrigemPericia;
import com.rpg.core.model.OrigemPericiaId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrigemPericiaService {

    private final OrigemPericiaRepository repository;

    public OrigemPericiaService(OrigemPericiaRepository repository) {
        this.repository = repository;
    }

    public List<OrigemPericia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<OrigemPericia> buscarPorId(OrigemPericiaId id) {
        return repository.buscarPorId(id);
    }

    public OrigemPericia salvar(OrigemPericia obj) {
        return repository.salvar(obj);
    }

    public void deletar(OrigemPericiaId id) {
        repository.deletar(id);
    }
}
