// src/main/java/com/rpg/core/service/MapaService.java
package com.rpg.core.service;

import com.rpg.adapter.in.dto.MapaSummary;
import com.rpg.adapter.out.MapaRepository;
import com.rpg.core.model.Mapa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MapaService {

    private final MapaRepository repository;

    public MapaService(MapaRepository repository) {
        this.repository = repository;
    }

    public List<Mapa> listarTodos() {
        return repository.findAll();
    }

    public Optional<Mapa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Mapa salvar(Mapa obj) {
        // se vier apenas jogo.idJogo no JSON, o JPA associa normal
        return repository.save(obj);
    }

    @Transactional
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    /* ===================== NOVOS MÉTODOS ===================== */

    /** Lista mapas de um jogo específico (usado pelo GET /mapas/jogo/{idJogo}) */
    public List<Mapa> listarPorJogo(Long idJogo) {
        return repository.findByJogo_IdJogo(idJogo);
    }

    /** Valida se um mapa pertence ao jogo informado (usado no WS) */
    public boolean mapaPertenceAoJogo(Long idMapa, Long idJogo) {
        return repository.existsByIdMapaAndJogo_IdJogo(idMapa, idJogo);
    }

    /* ===================== IMAGEM ===================== */

    @Transactional(readOnly = true)
    public List<MapaSummary> listarResumoPorJogo(Long idJogo) {
        return repository.listarResumoPorJogo(idJogo);
    }

    @Transactional(readOnly = true)
    public Optional<byte[]> obterImagem(Long id) {
        return Optional.ofNullable(repository.findImagemBytes(id));
    }

    @Transactional(readOnly = true)
    public Optional<String> obterImagemContentType(Long id) {
        return repository.findImagemContentType(id);
    }

    @Transactional
    public boolean atualizarImagem(Long id, byte[] bytes, String ct) {
        return repository.findById(id).map(m -> {
            m.setImagem(bytes);
            m.setImagemContentType(ct);
            repository.save(m);
            return true;
        }).orElse(false);
    }

    @Transactional
    public boolean removerImagem(Long id) {
        return repository.findById(id).map(m -> {
            m.setImagem(null);
            m.setImagemContentType(null);
            repository.save(m);
            return true;
        }).orElse(false);
    }
}
