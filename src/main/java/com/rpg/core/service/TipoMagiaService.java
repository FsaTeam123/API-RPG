package com.rpg.core.service;

import com.rpg.adapter.out.TipoMagiaRepository;
import com.rpg.core.model.TipoMagia;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMagiaService {

    private final TipoMagiaRepository repository;

    public TipoMagiaService(TipoMagiaRepository repository) {
        this.repository = repository;
    }

    public List<TipoMagia> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<TipoMagia> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public TipoMagia salvar(TipoMagia obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
