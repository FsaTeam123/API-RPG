package com.rpg.core.service;

import com.rpg.adapter.out.MagiaRepository;
import com.rpg.core.model.Magia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MagiaService {

    private final MagiaRepository repository;

    public MagiaService(MagiaRepository repository) {
        this.repository = repository;
    }

    public List<Magia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Magia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Magia salvar(Magia magia) {
        return repository.salvar(magia);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
