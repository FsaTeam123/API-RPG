package com.rpg.adapter.out;

import com.rpg.core.model.GeracaoMundo;
import com.rpg.port.output.GeracaoMundoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeracaoMundoRepository extends JpaRepository<GeracaoMundo, Long>, GeracaoMundoRepositoryInterface {

    @Override
    default List<GeracaoMundo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<GeracaoMundo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default GeracaoMundo salvar(GeracaoMundo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
