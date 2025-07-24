package com.rpg.adapter.out;

import com.rpg.core.model.Aprimoramento;
import com.rpg.port.output.AprimoramentoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AprimoramentoRepository extends JpaRepository<Aprimoramento, Long>, AprimoramentoRepositoryInterface {

    @Override
    default List<Aprimoramento> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Aprimoramento> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Aprimoramento salvar(Aprimoramento aprimoramento) {
        return save(aprimoramento);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
