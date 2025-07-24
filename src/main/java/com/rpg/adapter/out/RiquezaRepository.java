package com.rpg.adapter.out;

import com.rpg.core.model.Riqueza;
import com.rpg.port.output.RiquezaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RiquezaRepository extends JpaRepository<Riqueza, Long>, RiquezaRepositoryInterface {

    @Override
    default List<Riqueza> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Riqueza> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Riqueza salvar(Riqueza obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
