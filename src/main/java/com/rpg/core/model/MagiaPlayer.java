package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MagiaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAGIA_PLAYER")
    private Long idMagiaPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_MAGIA", nullable = false)
    private Magia magia;

    public MagiaPlayer(Long idMagiaPlayer) {
        this.idMagiaPlayer = idMagiaPlayer;
    }
}
