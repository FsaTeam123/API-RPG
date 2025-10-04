// com/rpg/adapter/in/dto/PlayerCreateDTO.java
package com.rpg.adapter.in.dto;

import jakarta.validation.constraints.*;

public record PlayerCreateDTO(
        @NotBlank String nome,

        // Atributos
        @Min(0) int forca,
        @Min(0) int destreza,
        @Min(0) int sabedoria,
        @Min(0) int constituicao,
        @Min(0) int inteligencia,
        @Min(0) int carisma,

        @Min(0) int pv,
        @Min(0) int pvMax,
        @Min(0) int pvTemp,

        @Min(0) int pm,
        @Min(0) int pmMax,
        @Min(0) int pmTemp,

        // Somente IDs das relações:
        @NotNull Long idUsuario,
        @NotNull Long idJogo,
        @NotNull Long idOrigem,
        @NotNull Long idRaca,
        @NotNull Long idRiqueza,
        @NotNull Long idDivindade,
        @NotNull Long idClasse,
        @NotNull Long idTamanho
) {}
