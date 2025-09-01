package com.rpg.port.output;

import com.rpg.core.model.Jogo;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JogoRepositoryInterface {

    List<Jogo> listarTodos();

    Optional<Jogo> buscarPorId(Long id);

    Jogo salvar(Jogo obj);

    void deletar(Long id);

    List<Jogo> buscarPorMestre(Long id);

    List<Jogo> buscarPorJogador(@Param("usuarioId") Long usuarioId);
}
