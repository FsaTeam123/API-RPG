package com.rpg.adapter.out;

import com.rpg.core.model.ClasseJogo;
import com.rpg.core.model.ClasseJogoId;
import com.rpg.port.output.ClasseJogoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseJogoRepository extends JpaRepository<ClasseJogo, ClasseJogoId>, ClasseJogoRepositoryInterface {

    @Override
    default List<ClasseJogo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<ClasseJogo> buscarPorId(ClasseJogoId id) {
        return findById(id);
    }

    @Override
    default ClasseJogo salvar(ClasseJogo obj) {
        return save(obj);
    }

    @Override
    default void deletar(ClasseJogoId id) {
        deleteById(id);
    }
}