package com.rpg.adapter.out;

import com.rpg.core.model.Divindade;
import com.rpg.port.output.DivindadeRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DivindadeRepository extends JpaRepository<Divindade, Long>, DivindadeRepositoryInterface {

    @Override
    default List<Divindade> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Divindade> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Divindade salvar(Divindade obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
