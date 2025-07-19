package com.rpg.adapter.out;

import com.rpg.core.model.Jogo;
import com.rpg.port.output.JogoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long>, JogoRepositoryInterface {

    @Override
    default List<Jogo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Jogo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Jogo salvar(Jogo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
