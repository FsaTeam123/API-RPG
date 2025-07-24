package com.rpg.adapter.out;

import com.rpg.core.model.EscolaMagia;
import com.rpg.port.output.EscolaMagiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EscolaMagiaRepository extends JpaRepository<EscolaMagia, Long>, EscolaMagiaRepositoryInterface {

    @Override
    default List<EscolaMagia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<EscolaMagia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default EscolaMagia salvar(EscolaMagia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
