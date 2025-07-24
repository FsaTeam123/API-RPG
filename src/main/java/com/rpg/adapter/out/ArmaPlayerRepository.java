package com.rpg.adapter.out;

import com.rpg.core.model.ArmaPlayer;
import com.rpg.port.output.ArmaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArmaPlayerRepository extends JpaRepository<ArmaPlayer, Long>, ArmaPlayerRepositoryInterface {

    @Override
    default List<ArmaPlayer> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<ArmaPlayer> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default ArmaPlayer salvar(ArmaPlayer obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
