package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrigemItens {

    @EmbeddedId
    private OrigemItensId id;

    @ManyToOne
    @MapsId("idOrigem")
    @JoinColumn(name = "ID_ORIGEM")
    private Origem origem;

    @ManyToOne
    @MapsId("idItens")
    @JoinColumn(name = "ID_ITENS")
    private Itens itens;

    private Integer ativo;
}
