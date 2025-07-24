package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArmaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARMA_PLAYER")
    private Long idArmaPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_ARMA", nullable = false)
    private Arma arma;

    public ArmaPlayer(Long idArmaPlayer) {
        this.idArmaPlayer = idArmaPlayer;
    }
}
