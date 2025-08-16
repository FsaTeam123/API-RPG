package com.rpg.port.output;

import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface CodigoVerificacaoRepositoryInterface {

    int consumirCodigo(@Param("email") String email,
                       @Param("codigo") String codigo,
                       @Param("agora") Instant agora);

    int invalidarAtivosPorEmail(@Param("email") String email);

    boolean existsByCodigo(String codigo);
}
