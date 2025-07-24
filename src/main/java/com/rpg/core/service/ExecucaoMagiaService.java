package com.rpg.core.service;

import com.rpg.adapter.out.ExecucaoMagiaRepository;
import com.rpg.core.model.ExecucaoMagia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExecucaoMagiaService {

    private final ExecucaoMagiaRepository repository;

    public ExecucaoMagiaService(ExecucaoMagiaRepository repository) {
        this.repository = repository;
    }

    public List<ExecucaoMagia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<ExecucaoMagia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public ExecucaoMagia salvar(ExecucaoMagia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
