package com.rpg.adapter.out;

import com.rpg.core.model.TipoItem;
import com.rpg.port.output.TipoItemRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoItemRepository extends JpaRepository<TipoItem, Long>, TipoItemRepositoryInterface {

    @Override
    default List<TipoItem> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoItem> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoItem salvar(TipoItem obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
