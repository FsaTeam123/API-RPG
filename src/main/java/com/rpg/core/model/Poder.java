package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Poder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PODER")
    private Long idPoder;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_TIPO_PODER", nullable = false)
    private TipoPoder tipoPoder;

    public Poder(Long idPoder) {
        this.idPoder = idPoder;
    }
}
