package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ModificadorId implements Serializable {

    @Column(name = "ID_RACA")
    private Long idRaca;

    @Column(name = "ID_ATRIBUTO")
    private Long idAtributo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ModificadorId that)) return false;
        return Objects.equals(idRaca, that.idRaca) &&
                Objects.equals(idAtributo, that.idAtributo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRaca, idAtributo);
    }
}
