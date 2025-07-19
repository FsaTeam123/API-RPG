package com.rpg.core.service;

import com.rpg.adapter.out.HistoriaRepository;
import com.rpg.core.model.Historia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoriaService {

    private final HistoriaRepository repository;

    public HistoriaService(HistoriaRepository repository) {
        this.repository = repository;
    }

    public List<Historia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Historia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Historia salvar(Historia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
