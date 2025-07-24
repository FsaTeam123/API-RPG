package com.rpg.adapter.out;

import com.rpg.core.model.PericiaPlayer;
import com.rpg.port.output.PericiaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PericiaPlayerRepository extends JpaRepository<PericiaPlayer, Long>, PericiaPlayerRepositoryInterface {

    @Override
    default List<PericiaPlayer> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<PericiaPlayer> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default PericiaPlayer salvar(PericiaPlayer obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
