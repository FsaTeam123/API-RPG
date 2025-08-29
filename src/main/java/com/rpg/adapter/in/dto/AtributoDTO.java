package com.rpg.adapter.in.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtributoDTO {
    private Long idAtributo;
    private String nome;       // ajuste aos campos existentes
    private String descricao;  // se existir
}
