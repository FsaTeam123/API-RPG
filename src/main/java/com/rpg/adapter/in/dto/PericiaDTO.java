package com.rpg.adapter.in.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PericiaDTO {
    private Long idPericia;
    private String nome;
    private String descricao;
    private Integer ativo;
}
