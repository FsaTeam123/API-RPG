package com.rpg.core.service;

import com.rpg.adapter.out.ProeficienciaRepository;
import com.rpg.core.model.Proeficiencia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProeficienciaService {

    private final ProeficienciaRepository repository;

    public ProeficienciaService(ProeficienciaRepository repository) {
        this.repository = repository;
    }

    public List<Proeficiencia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Proeficiencia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Proeficiencia salvar(Proeficiencia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
