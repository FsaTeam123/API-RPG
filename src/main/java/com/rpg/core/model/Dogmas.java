package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dogmas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOGMAS")
    private Long idDogmas;

    @ManyToOne
    @JoinColumn(name = "ID_DIVINDADE", nullable = false)
    private Divindade divindade;

    private String descricao;
    private Integer ativo;

    public Dogmas(Long idDogmas) {
        this.idDogmas = idDogmas;
    }
}
