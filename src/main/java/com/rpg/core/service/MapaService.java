package com.rpg.core.service;

import com.rpg.adapter.out.MapaRepository;
import com.rpg.core.model.Mapa;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MapaService {

    private final MapaRepository repository;

    public MapaService(MapaRepository repository) {
        this.repository = repository;
    }

    public List<Mapa> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Mapa> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Mapa salvar(Mapa obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}