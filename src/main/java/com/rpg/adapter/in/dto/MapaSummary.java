// src/main/java/com/rpg/adapter/in/dto/MapaSummary.java
package com.rpg.adapter.in.dto;

public record MapaSummary(
        Long idMapa,
        Long idJogo,
        String nome,
        String descricao,
        Integer grid,
        Integer ativo,
        boolean hasImage
) {}
