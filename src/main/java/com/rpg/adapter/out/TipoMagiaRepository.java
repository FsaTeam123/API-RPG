package com.rpg.adapter.out;

import com.rpg.core.model.TipoMagia;
import com.rpg.port.output.TipoMagiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TipoMagiaRepository extends JpaRepository<TipoMagia, Long>, TipoMagiaRepositoryInterface {

    @Override
    default List<TipoMagia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<TipoMagia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default TipoMagia salvar(TipoMagia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
