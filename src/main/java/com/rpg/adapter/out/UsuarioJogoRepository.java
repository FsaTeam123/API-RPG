package com.rpg.adapter.out;

import com.rpg.core.model.UsuarioJogo;
import com.rpg.core.model.UsuarioJogoId;
import com.rpg.port.output.UsuarioJogoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioJogoRepository extends JpaRepository<UsuarioJogo, Long>, UsuarioJogoRepositoryInterface {

    @Override
    default List<UsuarioJogo> listarTodos() {
        return findAll();
    }

    @Override
    default UsuarioJogo salvar(UsuarioJogo obj) {
        return save(obj);
    }
}
