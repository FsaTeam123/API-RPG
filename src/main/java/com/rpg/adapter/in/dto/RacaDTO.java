// src/main/java/com/rpg/adapter/in/dto/RacaDTO.java
package com.rpg.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RacaDTO {
    private Long idRaca;
    private String nome;
    private String descricao;
    private Integer ativo;

    // foto em Base64 (igual ClasseDTO.imagemBase64)
    private String fotoBase64;
    private String fotoMime;
    private String fotoNome;
    private Long   fotoTam;
    private Instant fotoAtualizadaEm;

    private List<HabilidadeRacaDTO> habilidades;

    // getters/setters ...
}
