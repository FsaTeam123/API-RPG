package com.rpg.adapter.out;

import com.rpg.core.model.Tamanho;
import com.rpg.port.output.TamanhoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TamanhoRepository extends JpaRepository<Tamanho, Long>, TamanhoRepositoryInterface {

    @Override
    default List<Tamanho> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Tamanho> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Tamanho salvar(Tamanho obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
