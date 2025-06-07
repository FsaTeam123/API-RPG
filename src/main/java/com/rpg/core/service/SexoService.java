package com.rpg.core.service;

import com.rpg.core.model.Sexo;
import com.rpg.adapter.out.SexoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SexoService {

    private final SexoRepository repository;

    public SexoService(SexoRepository repository) {
        this.repository = repository;
    }

    public List<Sexo> listarTodos() {
        return repository.findAll();
    }

    public Optional<Sexo> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Sexo salvar(Sexo obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}