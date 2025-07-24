package com.rpg.adapter.out;

import com.rpg.core.model.Pericia;
import com.rpg.port.output.PericiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PericiaRepository extends JpaRepository<Pericia, Long>, PericiaRepositoryInterface {

    @Override
    default List<Pericia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Pericia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Pericia salvar(Pericia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}