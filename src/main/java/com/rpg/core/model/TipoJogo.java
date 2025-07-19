package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoJogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_JOGO")
    private Long idTipoJogo;

    private String nome;

    public TipoJogo(Long idTipoJogo) {
        this.idTipoJogo = idTipoJogo;
    }
}
