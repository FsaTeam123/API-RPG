package com.rpg.core.service;

import com.rpg.adapter.out.ProeficienciaPlayerRepository;
import com.rpg.core.model.ProeficienciaClasse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProeficienciaPlayerService {

    private final ProeficienciaPlayerRepository repository;

    public ProeficienciaPlayerService(ProeficienciaPlayerRepository repository) {
        this.repository = repository;
    }

    public List<ProeficienciaClasse> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<ProeficienciaClasse> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public ProeficienciaClasse salvar(ProeficienciaClasse obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
