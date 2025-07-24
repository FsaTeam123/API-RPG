package com.rpg.adapter.out;

import com.rpg.core.model.TipoArma;
import com.rpg.port.output.TipoArmaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoArmaRepository extends JpaRepository<TipoArma, Long>, TipoArmaRepositoryInterface {

    @Override
    default List<TipoArma> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoArma> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoArma salvar(TipoArma obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
