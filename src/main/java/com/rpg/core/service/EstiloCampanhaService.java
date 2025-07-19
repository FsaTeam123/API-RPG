package com.rpg.core.service;

import com.rpg.adapter.out.EstiloCampanhaRepository;
import com.rpg.core.model.EstiloCampanha;
import com.rpg.port.output.EstiloCampanhaRepositoryInterface;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstiloCampanhaService {

    private final EstiloCampanhaRepository repository;

    public EstiloCampanhaService(EstiloCampanhaRepository repository) {
        this.repository = repository;
    }

    public List<EstiloCampanha> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<EstiloCampanha> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public EstiloCampanha salvar(EstiloCampanha obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
