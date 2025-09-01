package com.rpg.adapter.out;

import com.rpg.core.model.Raca;
import com.rpg.port.output.RacaRepositoryInterface;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Long>, RacaRepositoryInterface {

    @Override
    @EntityGraph(attributePaths = "habilidades")
    default List<Raca> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Raca> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Raca salvar(Raca obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
