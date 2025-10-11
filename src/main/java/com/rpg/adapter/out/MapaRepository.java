package com.rpg.adapter.out;

import com.rpg.adapter.in.dto.MapaSummary;
import com.rpg.core.model.Mapa;
import com.rpg.port.output.MapaRepositoryInterface;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MapaRepository extends JpaRepository<Mapa, Long>, MapaRepositoryInterface {

    @Override
    default List<Mapa> listarTodos() {
        return findAll();
    }

    @Override
    default Optional<Mapa> buscarPorId(Long id) {
        return findById(id);
    }

    @Override
    default Mapa salvar(Mapa obj) {
        return save(obj);
    }

    @Override
    default void deletar(Long id) {
        deleteById(id);
    }

    List<Mapa> findByJogo_IdJogo(Long idJogo);

    // ❌ existsByIdAndJogo_IdJogo(...)  // ERRADO (não existe 'id' em Mapa)
    // ✅ use o nome correto da PK:
    boolean existsByIdMapaAndJogo_IdJogo(Long idMapa, Long idJogo);

    @Query("""
        select new com.rpg.adapter.in.dto.MapaSummary(
            m.idMapa,
            m.jogo.idJogo,
            m.nome,
            m.descricao,
            m.grid,
            m.ativo,
            case when m.imagem is not null then true else false end
        )
        from Mapa m
        where m.jogo.idJogo = :idJogo
    """)
    List<MapaSummary> listarResumoPorJogo(@Param("idJogo") Long idJogo);

    // Endpoints de imagem sem carregar a entidade toda
    @Query("select m.imagem from Mapa m where m.idMapa = :id")
    byte[] findImagemBytes(@Param("id") Long id);

    @Query("select m.imagemContentType from Mapa m where m.idMapa = :id")
    Optional<String> findImagemContentType(@Param("id") Long id);

}