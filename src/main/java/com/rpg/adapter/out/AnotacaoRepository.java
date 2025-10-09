package com.rpg.adapter.out;

import com.rpg.core.model.Anotacao;
import com.rpg.port.output.AnotacaoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnotacaoRepository extends JpaRepository<Anotacao, Long>, AnotacaoRepositoryInterface {

    @Override
    default List<Anotacao> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Anotacao> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Anotacao salvar(Anotacao obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }

    @Override default List<Anotacao> buscarPorJogoId(Long jogoId) {
        return findByJogoId(jogoId);
    }
    @Query("select a from Anotacao a where a.jogo.idJogo = :jogoId")
    List<Anotacao> findByJogoId(@Param("jogoId") Long jogoId);

}
