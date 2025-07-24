package com.rpg.adapter.out;

import com.rpg.core.model.Atributo;
import com.rpg.port.output.AtributoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtributoRepository extends JpaRepository<Atributo, Long>, AtributoRepositoryInterface {

    @Override
    default List<Atributo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Atributo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Atributo salvar(Atributo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}