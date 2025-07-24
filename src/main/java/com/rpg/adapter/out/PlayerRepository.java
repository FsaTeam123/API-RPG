package com.rpg.adapter.out;

import com.rpg.core.model.Player;
import com.rpg.port.output.PlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryInterface {

    @Override
    default List<Player> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Player> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Player salvar(Player player) {
        return save(player);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
