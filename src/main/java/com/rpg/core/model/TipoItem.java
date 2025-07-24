package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_ITEM")
    private Long idTipoItem;

    private String nome;
    private String descricao;
    private Integer valor;
    private String dice;
    private Integer ativo;

    public TipoItem(Long idTipoItem) {
        this.idTipoItem = idTipoItem;
    }
}
