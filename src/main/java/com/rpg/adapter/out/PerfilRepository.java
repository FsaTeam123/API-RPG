package com.rpg.adapter.out;

import com.rpg.core.model.Perfil;
import com.rpg.port.output.PerfilRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Long>, PerfilRepositoryInterface {

    // Aqui, o JpaRepository já tem as implementações dos métodos como save, delete, etc.
    // Vamos delegar os métodos para o JpaRepository.

    @Override
    default List<Perfil> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Perfil> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Perfil salvar(Perfil obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}