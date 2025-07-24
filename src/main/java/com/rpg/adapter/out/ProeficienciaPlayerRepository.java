package com.rpg.adapter.out;

import com.rpg.core.model.ProeficienciaPlayer;
import com.rpg.port.output.ProeficienciaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProeficienciaPlayerRepository extends JpaRepository<ProeficienciaPlayer, Long>, ProeficienciaPlayerRepositoryInterface {

    @Override
    default List<ProeficienciaPlayer> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<ProeficienciaPlayer> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default ProeficienciaPlayer salvar(ProeficienciaPlayer obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
