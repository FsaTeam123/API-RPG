package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Divindade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DIVINDADE")
    private Long idDivindade;

    private String nome;
    private String descricao;
    private Integer ativo;

    public Divindade(Long idDivindade) {
        this.idDivindade = idDivindade;
    }
}
