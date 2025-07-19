package com.rpg.adapter.out;

import com.rpg.core.model.Historia;
import com.rpg.port.output.HistoriaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HistoriaRepository extends JpaRepository<Historia, Long>, HistoriaRepositoryInterface {

    @Override
    default List<Historia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Historia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Historia salvar(Historia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
