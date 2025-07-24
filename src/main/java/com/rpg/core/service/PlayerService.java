package com.rpg.core.service;

import com.rpg.adapter.out.PlayerRepository;
import com.rpg.core.model.Player;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public List<Player> listarTodos() {
        return repository.listarTodos();
    }

    public Optional<Player> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Player salvar(Player player) {
        return repository.salvar(player);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }
}
