package com.rpg.adapter.out;

import com.rpg.core.model.ExecucaoMagia;
import com.rpg.port.output.ExecucaoMagiaRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExecucaoMagiaRepository extends JpaRepository<ExecucaoMagia, Long>, ExecucaoMagiaRepositoryInterface {

    @Override
    default List<ExecucaoMagia> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<ExecucaoMagia> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default ExecucaoMagia salvar(ExecucaoMagia obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
