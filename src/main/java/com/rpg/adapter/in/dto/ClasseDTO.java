package com.rpg.adapter.in.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseDTO {
    private Long idClasse;
    private String nome;
    private String descricao;

    private Integer pvInit;
    private Integer pvNivel;

    private AtributoDTO atributoPV;

    private Integer pmInit;
    private Integer pmNivel;

    private Integer ativo;

    private String imagemBase64;
    private String imagemContentType;
    private String imagemFilename;

    private List<ProeficienciaDTO> proeficiencias;
    private List<PericiaDTO> pericias;
}
