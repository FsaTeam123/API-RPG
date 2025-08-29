package com.rpg.adapter.out;

import com.rpg.core.model.Classe;
import com.rpg.port.output.ClasseRepositoryInterface;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClasseRepository extends JpaRepository<Classe, Long>, ClasseRepositoryInterface {

    @Override
    @EntityGraph(attributePaths = {
            "atributoPV",
            "proeficienciasClasse",
            "proeficienciasClasse.proeficiencia",
            "periciasClasse",
            "periciasClasse.pericia"
    })
    default List<Classe> listarTodos() {
        return findAll();
    }

    @Override
    @EntityGraph(attributePaths = {
            "atributoPV",
            "proeficienciasClasse",
            "proeficienciasClasse.proeficiencia",
            "periciasClasse",
            "periciasClasse.pericia"
    })
    default Optional<Classe> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Classe salvar(Classe obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
