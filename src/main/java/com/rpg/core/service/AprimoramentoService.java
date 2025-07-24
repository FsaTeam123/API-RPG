package com.rpg.core.service;

import com.rpg.adapter.out.AprimoramentoRepository;
import com.rpg.core.model.Aprimoramento;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AprimoramentoService {

    private final AprimoramentoRepository repository;

    public AprimoramentoService(AprimoramentoRepository repository) {
        this.repository = repository;
    }

    public List<Aprimoramento> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Aprimoramento> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Aprimoramento salvar(Aprimoramento aprimoramento) {
        return repository.salvar(aprimoramento);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
