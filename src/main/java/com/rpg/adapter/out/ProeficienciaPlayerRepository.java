package com.rpg.adapter.out;

import com.rpg.core.model.ProeficienciaClasse;
import com.rpg.port.output.ProeficienciaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProeficienciaPlayerRepository extends JpaRepository<ProeficienciaClasse, Long>, ProeficienciaPlayerRepositoryInterface {

    @Override
    default List<ProeficienciaClasse> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<ProeficienciaClasse> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default ProeficienciaClasse salvar(ProeficienciaClasse obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
