// com/rpg/core/service/PericiaPlayerService.java
package com.rpg.core.service;

import com.rpg.adapter.in.dto.PericiaPlayerCreateDTO;
import com.rpg.adapter.out.PericiaPlayerRepository;
import com.rpg.adapter.out.PlayerRepository;
import com.rpg.adapter.out.PericiaRepository; // seu repo de Pericia
import com.rpg.core.model.PericiaPlayer;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PericiaPlayerService {

    private final PericiaPlayerRepository repository;
    private final PlayerRepository playerRepository;
    private final PericiaRepository periciaRepository;

    public PericiaPlayerService(PericiaPlayerRepository repository,
                                PlayerRepository playerRepository,
                                PericiaRepository periciaRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.periciaRepository = periciaRepository;
    }

    public List<PericiaPlayer> listarTodos() { return repository.listarTodos(); }
    public Optional<PericiaPlayer> buscarPorId(Long id) { return repository.buscarPorId(id); }
    public void deletar(Long id) { repository.deletar(id); }

    /** Novo: criar só com IDs */
    @Transactional
    public PericiaPlayer criarComIds(PericiaPlayerCreateDTO dto) {
        if (repository.existsByPlayer_IdPlayerAndPericia_IdPericia(dto.playerId(), dto.periciaId())) {
            throw new DataIntegrityViolationException(
                    "Vínculo já existe para playerId=%d e periciaId=%d".formatted(dto.playerId(), dto.periciaId()));
        }

        PericiaPlayer pp = new PericiaPlayer();
        pp.setPlayer(playerRepository.getReferenceById(dto.playerId()));   // proxy (sem select agora)
        pp.setPericia(periciaRepository.getReferenceById(dto.periciaId())); // proxy
        return repository.salvar(pp);
    }

    /** Novo: atualizar trocando os IDs */
    @Transactional
    public PericiaPlayer atualizarComIds(Long id, PericiaPlayerCreateDTO dto) {
        PericiaPlayer existente = repository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("PericiaPlayer %d não encontrado".formatted(id)));

        if (repository.existsByPlayer_IdPlayerAndPericia_IdPericia(dto.playerId(), dto.periciaId())) {
            throw new DataIntegrityViolationException(
                    "Vínculo já existe para playerId=%d e periciaId=%d".formatted(dto.playerId(), dto.periciaId()));
        }

        existente.setPlayer(playerRepository.getReferenceById(dto.playerId()));
        existente.setPericia(periciaRepository.getReferenceById(dto.periciaId()));
        return repository.salvar(existente);
    }

    /** Mantém se ainda houver chamadas antigas recebendo entidade inteira */
    public PericiaPlayer salvar(PericiaPlayer obj) { return repository.salvar(obj); }
}
