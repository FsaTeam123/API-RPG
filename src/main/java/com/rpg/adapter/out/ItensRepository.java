package com.rpg.adapter.out;

import com.rpg.core.model.Itens;
import com.rpg.port.output.ItensRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItensRepository extends JpaRepository<Itens, Long>, ItensRepositoryInterface {

    @Override
    default List<Itens> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Itens> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Itens salvar(Itens obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
