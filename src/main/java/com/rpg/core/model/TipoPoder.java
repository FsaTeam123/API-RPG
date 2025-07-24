package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoPoder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TIPO_PODER")
    private Long idTipoPoder;

    private String nome;
    private String descricao;

    public TipoPoder(Long idTipoPoder) {
        this.idTipoPoder = idTipoPoder;
    }
}
