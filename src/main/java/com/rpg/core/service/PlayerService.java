// com/rpg/core/service/PlayerService.java
package com.rpg.core.service;

import com.rpg.adapter.out.PlayerRepository;
import com.rpg.core.model.Player;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PlayerService {

    public record FotoPayload(byte[] bytes, String mime, String nome, Long tamanho) {}

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Player> listarTodos() {
        return repository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Optional<Player> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    public Player salvar(Player player) {
        return repository.salvar(player);
    }

    public void deletar(Long id) {
        repository.deletar(id);
    }

    // ===== FOTO (bytea) =====

    // Opção SIMPLES (carrega entidade e salva):
    public void salvarFoto(Long id, String originalFilename, String contentType, byte[] bytes) {
        Player p = repository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Player " + id + " não encontrado"));
        p.setFoto(bytes);
        p.setFotoMime(contentType);
        p.setFotoNome(originalFilename);
        p.setFotoTam(bytes != null ? (long) bytes.length : null);
        p.setFotoAtualizadaEm(Instant.now());
        repository.salvar(p);
    }

    @Transactional(readOnly = true)
    public Optional<FotoPayload> buscarFoto(Long id) {
        Optional<Player> opt = repository.buscarPorId(id);
        if (opt.isEmpty()) return Optional.empty();

        Player p = opt.get();
        if (p.getFoto() == null) return Optional.empty();

        return Optional.of(new FotoPayload(p.getFoto(), p.getFotoMime(), p.getFotoNome(), p.getFotoTam()));
    }

    public void removerFoto(Long id) {
        Player p = repository.buscarPorId(id)
                .orElseThrow(() -> new EntityNotFoundException("Player " + id + " não encontrado"));
        p.setFoto(null);
        p.setFotoMime(null);
        p.setFotoNome(null);
        p.setFotoTam(null);
        p.setFotoAtualizadaEm(Instant.now());
        repository.salvar(p);
    }

    @Transactional(readOnly = true)
    public List<Player> listarPorJogo(Long idJogo) {
        return repository.listarPorJogo(idJogo);
    }

    /* =====
     * Se preferir a OPÇÃO OTIMIZADA (sem carregar o BLOB),
     * basta descomentar estes trechos e implementar na repository (abaixo):
     *
     * public void salvarFoto(Long id, String originalFilename, String contentType, byte[] bytes) {
     *     int updated = repository.updateFoto(id, bytes, contentType, originalFilename,
     *                                         bytes != null ? (long) bytes.length : null);
     *     if (updated == 0) throw new EntityNotFoundException("Player " + id + " não encontrado");
     * }
     *
     * public void removerFoto(Long id) {
     *     int updated = repository.clearFoto(id);
     *     if (updated == 0) throw new EntityNotFoundException("Player " + id + " não encontrado");
     * }
     * ===== */
}
