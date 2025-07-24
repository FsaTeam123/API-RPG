package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrigemPericiaId implements Serializable {

    @Column(name = "ID_ORIGEM")
    private Long idOrigem;

    @Column(name = "ID_PERICIA")
    private Long idPericia;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrigemPericiaId that)) return false;
        return Objects.equals(idOrigem, that.idOrigem) && Objects.equals(idPericia, that.idPericia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrigem, idPericia);
    }
}
