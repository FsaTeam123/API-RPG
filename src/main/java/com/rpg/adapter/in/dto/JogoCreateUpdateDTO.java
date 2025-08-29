package com.rpg.adapter.in.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JogoCreateUpdateDTO {
    private Long masterId;

    private String titulo;
    private Integer qtdPessoas;
    private Integer isEspecificClass; // 0/1
    private Integer nivelInicial;

    private Long tipoJogoId;
    private Long geracaoMundoId;
    private Long estiloCampanhaId;
    private Long historiaId;
    private Long temaId;

    private String senha;
    private Integer ativo; // 0/1
}
