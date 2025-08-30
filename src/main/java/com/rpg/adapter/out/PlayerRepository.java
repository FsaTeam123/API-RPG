// com/rpg/adapter/out/PlayerRepository.java
package com.rpg.adapter.out;

import com.rpg.core.model.Player;
import com.rpg.port.output.PlayerRepositoryInterface;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryInterface {

    List<Player> findAllByJogo_IdJogo(Long idJogo);

    @Override
    default List<Player> listarTodos() { return findAll(); }

    @Override
    default Optional<Player> buscarPorId(Long id) { return findById(id); }

    @Override
    default Player salvar(Player player) { return save(player); }

    @Override
    default void deletar(Long id) { deleteById(id); }

    @Override
    default List<Player> listarPorJogo(Long idJogo) { return findAllByJogo_IdJogo(idJogo); }

    // ===== Otimizações de foto (opcional) =====
    @Modifying
    @Query("""
            update Player p
               set p.foto = :foto,
                   p.fotoMime = :mime,
                   p.fotoNome = :nome,
                   p.fotoTam  = :tam,
                   p.fotoAtualizadaEm = CURRENT_TIMESTAMP
             where p.idPlayer = :id
           """)
    int updateFoto(@Param("id") Long id,
                   @Param("foto") byte[] foto,
                   @Param("mime") String mime,
                   @Param("nome") String nome,
                   @Param("tam") Long tam);

    @Modifying
    @Query("""
            update Player p
               set p.foto = null,
                   p.fotoMime = null,
                   p.fotoNome = null,
                   p.fotoTam  = null,
                   p.fotoAtualizadaEm = CURRENT_TIMESTAMP
             where p.idPlayer = :id
           """)
    int clearFoto(@Param("id") Long id);
}
