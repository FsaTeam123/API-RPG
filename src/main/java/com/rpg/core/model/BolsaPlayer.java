package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BolsaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOLSA_PLAYER")
    private Long idBolsaPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_ITENS", nullable = false)
    private Itens item;

    @ManyToOne
    @JoinColumn(name = "ID_ARMA")
    private Arma arma;

    private int qtd;
    private int peso;
    private int preco;
    private String descricao;

    public BolsaPlayer(Long idBolsaPlayer) {
        this.idBolsaPlayer = idBolsaPlayer;
    }
}
