package com.rpg.core.service;

import com.rpg.adapter.out.ModificadorRepository;
import com.rpg.core.model.Modificador;
import com.rpg.core.model.ModificadorId;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModificadorService {

    private final ModificadorRepository repository;

    public ModificadorService(ModificadorRepository repository) {
        this.repository = repository;
    }

    public List<Modificador> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Modificador> buscarPorId(ModificadorId id) {
        return repository.buscarPorId(id);
    }

    public Modificador salvar(Modificador obj) {
        return repository.salvar(obj);
    }

    public void deletar(ModificadorId id) {
        repository.deletar(id);
    }
}
