package com.rpg.adapter.out;

import com.rpg.core.model.OrigemItens;
import com.rpg.core.model.OrigemItensId;
import com.rpg.port.output.OrigemItensRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrigemItensRepository extends JpaRepository<OrigemItens, OrigemItensId>, OrigemItensRepositoryInterface {

    @Override
    default List<OrigemItens> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<OrigemItens> buscarPorId(OrigemItensId id) {
        return findById(id);
    }

    @Override
    default OrigemItens salvar(OrigemItens obj) {
        return save(obj);
    }

    @Override
    default void deletar(OrigemItensId id) {
        deleteById(id);
    }
}
