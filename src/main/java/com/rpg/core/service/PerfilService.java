package com.rpg.core.service;

import com.rpg.core.model.Perfil;
import com.rpg.adapter.out.PerfilRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    private final PerfilRepository repository;

    public PerfilService(PerfilRepository repository) {
        this.repository = repository;
    }

    public List<Perfil> listarTodos() {
        return repository.findAll();
    }

    public Optional<Perfil> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Perfil salvar(Perfil obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}