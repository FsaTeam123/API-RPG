package com.rpg.adapter.out;

import com.rpg.core.model.OficioJogo;
import com.rpg.port.output.OficioJogoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OficioJogoRepository extends JpaRepository<OficioJogo, Long>, OficioJogoRepositoryInterface {

    @Override
    default List<OficioJogo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<OficioJogo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default OficioJogo salvar(OficioJogo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
