package com.rpg.adapter.out;

import com.rpg.core.model.Poder;
import com.rpg.port.output.PoderRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoderRepository extends JpaRepository<Poder, Long>, PoderRepositoryInterface {

    @Override
    default List<Poder> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Poder> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Poder salvar(Poder poder) {
        return save(poder);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
