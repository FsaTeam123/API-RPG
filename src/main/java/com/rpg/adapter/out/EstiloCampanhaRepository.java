package com.rpg.adapter.out;

import com.rpg.core.model.EstiloCampanha;
import com.rpg.port.output.EstiloCampanhaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstiloCampanhaRepository extends JpaRepository<EstiloCampanha, Long>, EstiloCampanhaRepositoryInterface {

    @Override
    default List<EstiloCampanha> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<EstiloCampanha> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default EstiloCampanha salvar(EstiloCampanha obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
