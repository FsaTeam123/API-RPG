package com.rpg.adapter.out;

import com.rpg.core.model.Tema;
import com.rpg.port.output.TemaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TemaRepository extends JpaRepository<Tema, Long>, TemaRepositoryInterface {

    @Override
    default List<Tema> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Tema> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Tema salvar(Tema obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
