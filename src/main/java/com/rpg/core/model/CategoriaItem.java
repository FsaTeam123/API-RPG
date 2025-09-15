package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class    CategoriaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA_ITEM")
    private Long idCategoriaItem;

    private String nome;
    private String descricao;
    private Integer ativo;

    public CategoriaItem(Long idCategoriaItem) {
        this.idCategoriaItem = idCategoriaItem;
    }
}
