package com.rpg.adapter.out;

import com.rpg.core.model.Usuario;
import com.rpg.port.output.UsuarioRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryInterface {

    // Métodos delegados para o JpaRepository sem recursão

    @Override
    default List<Usuario> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Usuario> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    Optional<Usuario> findByEmail(String email);

    @Override
    default Usuario salvar(Usuario obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }
}
