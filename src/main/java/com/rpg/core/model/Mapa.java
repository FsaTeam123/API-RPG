package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mapa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAPA")
    private Long idMapa;

    @ManyToOne
    @JoinColumn(name = "ID_JOGO", nullable = false)
    private Jogo jogo;

    private String descricao;
    private String nome;
    private Integer grid;

    @Lob
    private byte[] imagem;

    public Mapa(Long idMapa) {
        this.idMapa = idMapa;
    }
}