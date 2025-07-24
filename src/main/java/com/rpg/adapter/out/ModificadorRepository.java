package com.rpg.adapter.out;

import com.rpg.core.model.Modificador;
import com.rpg.core.model.ModificadorId;
import com.rpg.port.output.ModificadorRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModificadorRepository extends JpaRepository<Modificador, ModificadorId>, ModificadorRepositoryInterface {

    @Override
    default List<Modificador> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Modificador> buscarPorId(ModificadorId id) {
        return findById(id);
    }

    @Override
    default Modificador salvar(Modificador obj) {
        return save(obj);
    }

    @Override
    default void deletar(ModificadorId id) {
        deleteById(id);
    }
}
