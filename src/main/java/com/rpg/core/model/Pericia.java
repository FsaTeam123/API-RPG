package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pericia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERICIA")
    private Long idPericia;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_ATRIBUTO", nullable = false)
    private Atributo atributo;

    private Integer ativo;

    public Pericia(Long idPericia) {
        this.idPericia = idPericia;
    }
}
