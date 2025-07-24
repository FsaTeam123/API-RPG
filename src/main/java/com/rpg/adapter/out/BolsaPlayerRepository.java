package com.rpg.adapter.out;

import com.rpg.core.model.BolsaPlayer;
import com.rpg.port.output.BolsaPlayerRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BolsaPlayerRepository extends JpaRepository<BolsaPlayer, Long>, BolsaPlayerRepositoryInterface {

    @Override
    default List<BolsaPlayer> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<BolsaPlayer> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default BolsaPlayer salvar(BolsaPlayer obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
