package com.rpg.adapter.out;

import com.rpg.core.model.Sexo;
import com.rpg.port.output.SexoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface SexoRepository extends JpaRepository<Sexo, Long>, SexoRepositoryInterface {

    // Aqui, o JpaRepository já tem as implementações dos métodos como save, delete, etc.
    // Vamos delegar os métodos para o JpaRepository.

    @Override
    default List<Sexo> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Sexo> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Sexo salvar(Sexo obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
