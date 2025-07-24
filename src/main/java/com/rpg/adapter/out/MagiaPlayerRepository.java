package com.rpg.adapter.out;

import com.rpg.core.model.MagiaPlayer;
import com.rpg.port.output.MagiaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagiaPlayerRepository extends JpaRepository<MagiaPlayer, Long>, MagiaPlayerRepositoryInterface {

    @Override
    default List<MagiaPlayer> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<MagiaPlayer> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default MagiaPlayer salvar(MagiaPlayer obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
