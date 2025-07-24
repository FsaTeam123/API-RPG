package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseJogo {

    @EmbeddedId
    private ClasseJogoId id;

    @ManyToOne
    @MapsId("idClasse")
    @JoinColumn(name = "ID_CLASSE")
    private Classe classe;

    @ManyToOne
    @MapsId("idJogo")
    @JoinColumn(name = "ID_JOGO")
    private Jogo jogo;
}
