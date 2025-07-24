package com.rpg.adapter.out;

import com.rpg.core.model.TipoPoder;
import com.rpg.port.output.TipoPoderRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoPoderRepository extends JpaRepository<TipoPoder, Long>, TipoPoderRepositoryInterface {

    @Override
    default List<TipoPoder> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoPoder> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoPoder salvar(TipoPoder obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
