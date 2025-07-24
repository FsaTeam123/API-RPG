package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Origem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ORIGEM")
    private Long idOrigem;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Origem(Long idOrigem) {
        this.idOrigem = idOrigem;
    }
}
