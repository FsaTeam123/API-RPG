package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Atributo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ATRIBUTO")
    private Long idAtributo;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Atributo(Long idAtributo) {
        this.idAtributo = idAtributo;
    }
}