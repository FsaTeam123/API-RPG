package com.rpg.adapter.out;

import com.rpg.core.model.TipoJogo;
import com.rpg.port.output.TipoJogoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoJogoRepository extends JpaRepository<TipoJogo, Long>, TipoJogoRepositoryInterface {

    @Override
    default List<TipoJogo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoJogo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoJogo salvar(TipoJogo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
