// com/rpg/core/service/PlayerService.java
package com.rpg.core.service;

import com.rpg.adapter.in.dto.PlayerCreateDTO;
import com.rpg.adapter.out.*;
import com.rpg.core.model.Jogo;
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
    private final UsuarioRepository usuarioRepository;
    private final JogoRepository jogoRepository;
    private final OrigemRepository origemRepository;
    private final RacaRepository racaRepository;
    private final RiquezaRepository riquezaRepository;
    private final DivindadeRepository divindadeRepository;
    private final ClasseRepository classeRepository;
    private final TamanhoRepository tamanhoRepository;

    public PlayerService(PlayerRepository playerRepository,
                         UsuarioRepository usuarioRepository,
                         JogoRepository jogoRepository,
                         OrigemRepository origemRepository,
                         RacaRepository racaRepository,
                         RiquezaRepository riquezaRepository,
                         DivindadeRepository divindadeRepository,
                         ClasseRepository classeRepository,
                         TamanhoRepository tamanhoRepository) {
        this.repository = playerRepository;
        this.usuarioRepository = usuarioRepository;
        this.jogoRepository = jogoRepository;
        this.origemRepository = origemRepository;
        this.racaRepository = racaRepository;
        this.riquezaRepository = riquezaRepository;
        this.divindadeRepository = divindadeRepository;
        this.classeRepository = classeRepository;
        this.tamanhoRepository = tamanhoRepository;
    }

    @Transactional(readOnly = true)
    public List<Player> listarTodos() {
        return repository.listarTodos();
    }

    @Transactional(readOnly = true)
    public Optional<Player> buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }

    @Transactional(readOnly = true)
    public Player getPorId(Long id) {
        return repository.getPorId(id);
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
    @Transactional
    public Player criarAPartirDeIds(PlayerCreateDTO dto) {
        Player p = new Player();

        // Campos simples
        p.setNome(dto.nome());
        p.setForca(dto.forca());
        p.setDestreza(dto.destreza());
        p.setSabedoria(dto.sabedoria());
        p.setConstituicao(dto.constituicao());
        p.setInteligencia(dto.inteligencia());
        p.setCarisma(dto.carisma());

        p.setPv(dto.pv());
        p.setPvMax(dto.pvMax());
        p.setPvTemp(dto.pvTemp());

        p.setPm(dto.pm());
        p.setPmMax(dto.pmMax());
        p.setPmTemp(dto.pmTemp());

        // Relações via "proxy" (não carrega do banco agora):
        // getReferenceById lança EntityNotFound em flush se o ID não existir;
        // se quiser validar antecipadamente, use existsById(...) e lance 404.
        p.setUsuario(usuarioRepository.getReferenceById(dto.idUsuario()));
        p.setJogo(jogoRepository.getReferenceById(dto.idJogo()));
        p.setOrigem(origemRepository.getReferenceById(dto.idOrigem()));
        p.setRaca(racaRepository.getReferenceById(dto.idRaca()));
        p.setRiqueza(riquezaRepository.getReferenceById(dto.idRiqueza()));
        p.setDivindade(divindadeRepository.getReferenceById(dto.idDivindade()));
        p.setClasse(classeRepository.getReferenceById(dto.idClasse()));
        p.setTamanho(tamanhoRepository.getReferenceById(dto.idTamanho()));

        Jogo newJogo = new Jogo(p.getJogo().getIdJogo());
        jogoRepository.atualizaNumeroDeParticipantes(newJogo);

        return repository.save(p);
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
