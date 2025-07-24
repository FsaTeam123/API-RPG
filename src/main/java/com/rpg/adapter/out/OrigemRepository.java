package com.rpg.adapter.out;

import com.rpg.core.model.Origem;
import com.rpg.port.output.OrigemRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Long>, OrigemRepositoryInterface {

    @Override
    default List<Origem> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Origem> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Origem salvar(Origem obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
