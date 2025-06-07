package com.rpg.adapter.out;

import com.rpg.core.model.Notificacao;
import com.rpg.port.output.NotificacaoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface NotificacaoRepository extends JpaRepository<Notificacao, Long>, NotificacaoRepositoryInterface {

    // Aqui, o JpaRepository já tem as implementações dos métodos como save, delete, etc.
    // Vamos delegar os métodos para o JpaRepository.

    @Override
    default List<Notificacao> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Notificacao> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Notificacao salvar(Notificacao obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
