package com.rpg.adapter.out;

import com.rpg.core.model.PoderPlayer;
import com.rpg.port.output.PoderPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoderPlayerRepository extends JpaRepository<PoderPlayer, Long>, PoderPlayerRepositoryInterface {

    boolean existsByPlayer_IdPlayerAndPoder_IdPoder(Long playerId, Long poderId);

    List<PoderPlayer> findAllByPlayer_IdPlayer(Long playerId);
    List<PoderPlayer> findAllByPoder_IdPoder(Long poderId);

    @Override default List<PoderPlayer> listarTodos() { return findAll(); }
    @Override default Optional<PoderPlayer> buscarPorId(Long id) { return findById(id); }
    @Override default PoderPlayer salvar(PoderPlayer obj) { return save(obj); }
    @Override default void deletar(Long id) { deleteById(id); }
}
