package com.rpg.core.service;

import com.rpg.adapter.out.AcaoRepository;
import com.rpg.core.model.Acao;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcaoService {

    private final AcaoRepository repository;

    public AcaoService(AcaoRepository repository) {
        this.repository = repository;
    }

    public List<Acao> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Acao> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Acao salvar(Acao obj) {
        return repository.salvar(obj);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
