package com.rpg.service;

import com.rpg.entity.Notificacao;
import com.rpg.repository.NotificacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacaoService {

    private final NotificacaoRepository repository;

    public NotificacaoService(NotificacaoRepository repository) {
        this.repository = repository;
    }

    public List<Notificacao> listarTodos() {
        return repository.findAll();
    }

    public Optional<Notificacao> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Notificacao salvar(Notificacao obj) {
        return repository.save(obj);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}