package com.rpg.adapter.out;

import com.rpg.core.model.TipoDano;
import com.rpg.port.output.TipoDanoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoDanoRepository extends JpaRepository<TipoDano, Long>, TipoDanoRepositoryInterface {

    @Override
    default List<TipoDano> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoDano> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoDano salvar(TipoDano obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
