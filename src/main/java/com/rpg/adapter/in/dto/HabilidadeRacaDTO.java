// src/main/java/com/rpg/adapter/in/dto/HabilidadeRacaDTO.java
package com.rpg.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabilidadeRacaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer ativo;
    // getters/setters ...
}
