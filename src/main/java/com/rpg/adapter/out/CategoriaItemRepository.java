package com.rpg.adapter.out;

import com.rpg.core.model.CategoriaItem;
import com.rpg.port.output.CategoriaItemRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoriaItemRepository extends JpaRepository<CategoriaItem, Long>, CategoriaItemRepositoryInterface {

    @Override
    default List<CategoriaItem> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<CategoriaItem> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default CategoriaItem salvar(CategoriaItem obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
