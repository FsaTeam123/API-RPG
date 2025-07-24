package com.rpg.adapter.out;

import com.rpg.core.model.Magia;
import com.rpg.port.output.MagiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MagiaRepository extends JpaRepository<Magia, Long>, MagiaRepositoryInterface {

    @Override
    default List<Magia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Magia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Magia salvar(Magia magia) {
        return save(magia);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
