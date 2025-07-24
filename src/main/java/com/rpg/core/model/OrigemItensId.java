package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrigemItensId implements Serializable {

    @Column(name = "ID_ORIGEM")
    private Long idOrigem;

    @Column(name = "ID_ITENS")
    private Long idItens;

    // Equals e HashCode são obrigatórios para chaves compostas
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrigemItensId that)) return false;
        return Objects.equals(idOrigem, that.idOrigem) && Objects.equals(idItens, that.idItens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrigem, idItens);
    }
}
