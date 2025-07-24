package com.rpg.adapter.out;

import com.rpg.core.model.Acao;
import com.rpg.port.output.AcaoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AcaoRepository extends JpaRepository<Acao, Long>, AcaoRepositoryInterface {

    @Override
    default List<Acao> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Acao> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Acao salvar(Acao obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
