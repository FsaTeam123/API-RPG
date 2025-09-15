// com/rpg/adapter/in/dto/PlayerResponseDTO.java
package com.rpg.adapter.in.dto;

public record PlayerResponseDTO(
        Long idPlayer,
        String nome,
        int forca, int destreza, int sabedoria, int constituicao, int inteligencia, int carisma,
        int pv, int pvMax, int pvTemp,
        int pm, int pmMax, int pmTemp,
        Long idUsuario, Long idJogo, Long idOrigem, Long idRaca, Long idRiqueza, Long idDivindade, Long idClasse, Long idTamanho
) {}
