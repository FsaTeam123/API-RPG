package com.rpg.adapter.out;

import com.rpg.core.model.Arma;
import com.rpg.port.output.ArmaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArmaRepository extends JpaRepository<Arma, Long>, ArmaRepositoryInterface {

    @Override
    default List<Arma> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Arma> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Arma salvar(Arma obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
