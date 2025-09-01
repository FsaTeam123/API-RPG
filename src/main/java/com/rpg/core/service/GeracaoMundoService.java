package com.rpg.core.service;

import com.rpg.adapter.out.GeracaoMundoRepository;
import com.rpg.core.model.GeracaoMundo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeracaoMundoService {

    private final GeracaoMundoRepository repository;

    public GeracaoMundoService(GeracaoMundoRepository repository) {
        this.repository = repository;
    }

    public List<GeracaoMundo> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<GeracaoMundo> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public GeracaoMundo salvar(GeracaoMundo obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
