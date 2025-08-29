package com.rpg.adapter.in.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProeficienciaDTO {
    private Long idProeficiencia;
    private String nome;
    private String descricao;
    private Integer ativo;
}
