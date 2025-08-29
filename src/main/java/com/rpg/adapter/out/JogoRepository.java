package com.rpg.adapter.out;

import com.rpg.core.model.Jogo;
import com.rpg.port.output.JogoRepositoryInterface;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>, JogoRepositoryInterface {

    @Override
    default List<Jogo> listarTodos() { return findAll(); }

    @Override
    default Optional<Jogo> buscarPorId(Long id) { return findById(id); }

    @Override
    default Jogo salvar(Jogo obj) { return save(obj); }

    @Override
    default void deletar(Long id) { deleteById(id); }

    // Já existente: jogos por mestre
    List<Jogo> findByMaster_IdUsuario(Long masterId);

    @Override
    default List<Jogo> buscarPorMestre(Long id) { return findByMaster_IdUsuario(id); }

    @Override
    @Query(value = """
        SELECT DISTINCT j.titulo
        FROM public.usuario_jogo uj
        INNER JOIN public.jogo j ON uj.id_jogo = j.id_jogo
        WHERE uj.id_usuario = :usuarioId
        ORDER BY j.titulo
    """, nativeQuery = true)
    List<String> buscarPorJogador(@Param("usuarioId") Long usuarioId);
}
