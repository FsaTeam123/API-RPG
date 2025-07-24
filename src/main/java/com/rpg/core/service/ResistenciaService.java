package com.rpg.core.service;

import com.rpg.adapter.out.ResistenciaRepository;
import com.rpg.core.model.Resistencia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResistenciaService {

    private final ResistenciaRepository repository;

    public ResistenciaService(ResistenciaRepository repository) {
        this.repository = repository;
    }

    public List<Resistencia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Resistencia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Resistencia salvar(Resistencia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
