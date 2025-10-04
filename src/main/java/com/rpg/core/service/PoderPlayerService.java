// com/rpg/core/service/PoderPlayerService.java
package com.rpg.core.service;

import com.rpg.adapter.in.dto.PoderPlayerCreateDTO;
import com.rpg.adapter.out.PoderPlayerRepository;
import com.rpg.adapter.out.PlayerRepository;
import com.rpg.adapter.out.PoderRepository; // supondo que exista
import com.rpg.core.model.PoderPlayer;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class PoderPlayerService {

    private final PoderPlayerRepository repository;
    private final PlayerRepository playerRepository;
    private final PoderRepository poderRepository;

    public PoderPlayerService(PoderPlayerRepository repository,
                              PlayerRepository playerRepository,
                              PoderRepository poderRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
        this.poderRepository = poderRepository;
    }

    public List<PoderPlayer> listarTodos() { return repository.listarTodos(); }
    public Optional<PoderPlayer> buscarPorId(Long id) { return repository.buscarPorId(id); }
    public void deletar(Long id) { repository.deletar(id); }

    /** Cria vínculo usando apenas IDs */
    @Transactional
    public PoderPlayer criarComIds(PoderPlayerCreateDTO dto) {
        // valida duplicidade antes de tentar salvar (gera 409 bonito em vez de 500)
        if (repository.existsByPlayer_IdPlayerAndPoder_IdPoder(dto.playerId(), dto.poderId())) {
            throw new DataIntegrityViolationException("Vínculo já existe para playerId=" + dto.playerId() +
                    " e poderId=" + dto.poderId());
        }

        PoderPlayer pp = new PoderPlayer();
        // proxies (não hitam o banco agora; lançarão EntityNotFound no flush se ID não existir)
        pp.setPlayer(playerRepository.getReferenceById(dto.playerId()));
        pp.setPoder(poderRepository.getReferenceById(dto.poderId()));
        return repository.save(pp);
    }

    /** Atualiza vínculo trocando IDs (PUT) */
    @Transactional
    public PoderPlayer atualizarComIds(Long id, PoderPlayerCreateDTO dto) {
        PoderPlayer existente = repository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("PoderPlayer " + id + " não encontrado"));

        // se estiver trocando para um par já existente, barre
        if (repository.existsByPlayer_IdPlayerAndPoder_IdPoder(dto.playerId(), dto.poderId())) {
            throw new DataIntegrityViolationException("Vínculo já existe para playerId=" + dto.playerId() +
                    " e poderId=" + dto.poderId());
        }

        existente.setPlayer(playerRepository.getReferenceById(dto.playerId()));
        existente.setPoder(poderRepository.getReferenceById(dto.poderId()));
        return repository.salvar(existente);
    }

    /** Mantém o método antigo para quem ainda envia o objeto inteiro (se precisar) */
    public PoderPlayer salvar(PoderPlayer obj) { return repository.salvar(obj); }
}
