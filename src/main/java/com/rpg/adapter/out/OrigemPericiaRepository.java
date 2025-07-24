package com.rpg.adapter.out;

import com.rpg.core.model.OrigemPericia;
import com.rpg.core.model.OrigemPericiaId;
import com.rpg.port.output.OrigemPericiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrigemPericiaRepository extends JpaRepository<OrigemPericia, OrigemPericiaId>, OrigemPericiaRepositoryInterface {

    @Override
    default List<OrigemPericia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<OrigemPericia> buscarPorId(OrigemPericiaId id) {
        return findById(id);
    }

    @Override
    default OrigemPericia salvar(OrigemPericia obj) {
        return save(obj);
    }

    @Override
    default void deletar(OrigemPericiaId id) {
        deleteById(id);
    }
}
