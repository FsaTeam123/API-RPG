package com.rpg.core.service;

import com.rpg.adapter.out.PericiaRepository;
import com.rpg.core.model.Pericia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PericiaService {

    private final PericiaRepository repository;

    public PericiaService(PericiaRepository repository) {
        this.repository = repository;
    }

    public List<Pericia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Pericia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Pericia salvar(Pericia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
