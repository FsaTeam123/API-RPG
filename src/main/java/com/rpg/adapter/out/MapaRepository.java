package com.rpg.adapter.out;

import com.rpg.core.model.Mapa;
import com.rpg.port.output.MapaRepositoryInterface;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long>, MapaRepositoryInterface {

    @Override
    default List<Mapa> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Mapa> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Mapa salvar(Mapa obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}