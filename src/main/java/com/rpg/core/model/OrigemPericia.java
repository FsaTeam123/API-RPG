package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrigemPericia {

    @EmbeddedId
    private OrigemPericiaId id;

    @ManyToOne
    @MapsId("idOrigem")
    @JoinColumn(name = "ID_ORIGEM")
    private Origem origem;

    @ManyToOne
    @MapsId("idPericia")
    @JoinColumn(name = "ID_PERICIA")
    private Pericia pericia;

    private Integer ativo;
}