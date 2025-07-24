package com.rpg.adapter.out;

import com.rpg.core.model.Resistencia;
import com.rpg.port.output.ResistenciaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResistenciaRepository extends JpaRepository<Resistencia, Long>, ResistenciaRepositoryInterface {

    @Override
    default List<Resistencia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Resistencia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Resistencia salvar(Resistencia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
