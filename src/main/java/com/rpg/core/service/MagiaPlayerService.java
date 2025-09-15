// com/rpg/core/service/MagiaPlayerService.java
package com.rpg.core.service;

import com.rpg.adapter.in.dto.MagiaPlayerCreateDTO;
import com.rpg.adapter.out.MagiaPlayerRepository;
import com.rpg.adapter.out.PlayerRepository;
import com.rpg.adapter.out.MagiaRepository; // seu repo de Magia
import com.rpg.core.model.MagiaPlayer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MagiaPlayerService {

    private final MagiaPlayerRepository repository;
    private final PlayerRepository playerRepository;
    private final MagiaRepository magiaRepository;

    public MagiaPlayerService(MagiaPlayerRepository repository,
                              PlayerRepository playerRepository,
                              MagiaRepository magiaRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.magiaRepository = magiaRepository;
    }

    public List<MagiaPlayer> listarTodos() { return repository.listarTodos(); }
    public Optional<MagiaPlayer> buscarPorId(Long id) { return repository.buscarPorId(id); }
    public void deletar(Long id) { repository.deletar(id); }

    @Transactional
    public MagiaPlayer criarComIds(MagiaPlayerCreateDTO dto) {
        if (repository.existsByPlayer_IdPlayerAndMagia_IdMagia(dto.playerId(), dto.magiaId())) {
            throw new DataIntegrityViolationException(
                    "Vínculo já existe para playerId=%d e magiaId=%d".formatted(dto.playerId(), dto.magiaId()));
        }
        MagiaPlayer mp = new MagiaPlayer();
        mp.setPlayer(playerRepository.getReferenceById(dto.playerId())); // proxy
        mp.setMagia(magiaRepository.getReferenceById(dto.magiaId()));   // proxy
        return repository.salvar(mp);
    }

    @Transactional
    public MagiaPlayer atualizarComIds(Long id, MagiaPlayerCreateDTO dto) {
        MagiaPlayer existente = repository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("MagiaPlayer %d não encontrado".formatted(id)));

        if (repository.existsByPlayer_IdPlayerAndMagia_IdMagia(dto.playerId(), dto.magiaId())) {
            throw new DataIntegrityViolationException(
                    "Vínculo já existe para playerId=%d e magiaId=%d".formatted(dto.playerId(), dto.magiaId()));
        }

        existente.setPlayer(playerRepository.getReferenceById(dto.playerId()));
        existente.setMagia(magiaRepository.getReferenceById(dto.magiaId()));
        return repository.salvar(existente);
    }

    /** Mantém compatibilidade se ainda chamarem com a entidade inteira */
    public MagiaPlayer salvar(MagiaPlayer obj) { return repository.salvar(obj); }
}
