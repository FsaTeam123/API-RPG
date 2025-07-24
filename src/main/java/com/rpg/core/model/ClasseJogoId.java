package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClasseJogoId implements Serializable {

    @Column(name = "ID_CLASSE")
    private Long idClasse;

    @Column(name = "ID_JOGO")
    private Long idJogo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClasseJogoId that)) return false;
        return Objects.equals(idClasse, that.idClasse) &&
                Objects.equals(idJogo, that.idJogo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClasse, idJogo);
    }
}
