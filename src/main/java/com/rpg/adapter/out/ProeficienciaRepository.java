package com.rpg.adapter.out;

import com.rpg.core.model.Proeficiencia;
import com.rpg.port.output.ProeficienciaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProeficienciaRepository extends JpaRepository<Proeficiencia, Long>, ProeficienciaRepositoryInterface {

    @Override
    default List<Proeficiencia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Proeficiencia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Proeficiencia salvar(Proeficiencia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
