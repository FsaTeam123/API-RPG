package com.rpg.adapter.out;

import com.rpg.core.model.CodigoVerificacao;
import com.rpg.port.output.CodigoVerificacaoRepositoryInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;

@Repository
public interface CodigoVerificacaoRepository extends JpaRepository<CodigoVerificacao, Long>, CodigoVerificacaoRepositoryInterface {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
           UPDATE CodigoVerificacao c
              SET c.usado = true
            WHERE c.email = :email
              AND c.codigo = :codigo
              AND c.usado = false
              AND c.expiraEm > :agora
           """)
    @Override
    int consumirCodigo(@Param("email") String email,
                       @Param("codigo") String codigo,
                       @Param("agora") Instant agora);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
           UPDATE CodigoVerificacao c
              SET c.usado = true
            WHERE c.email = :email
              AND c.usado = false
           """)
    @Override
    int invalidarAtivosPorEmail(@Param("email") String email);


}
