package com.rpg.adapter.out;

import com.rpg.core.model.Dogmas;
import com.rpg.port.output.DogmasRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DogmasRepository extends JpaRepository<Dogmas, Long>, DogmasRepositoryInterface {

    @Override
    default List<Dogmas> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Dogmas> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Dogmas salvar(Dogmas obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
