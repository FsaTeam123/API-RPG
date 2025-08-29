package com.rpg.adapter.in.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseCreateUpdateDTO {
    private String nome;
    private String descricao;
    private Integer pvInit;
    private Integer pvNivel;
    private Long atributoPVId;
    private Integer pmInit;
    private Integer pmNivel;
    private Integer ativo;

    // imagem via multipart OU base64
    private String imagemBase64;
    private String imagemContentType;
    private String imagemFilename;

    // 👇 NOVO: ids de proeficiências a vincular
    private List<Long> proeficienciaIds;
    private List<Long> periciaIds;
}
