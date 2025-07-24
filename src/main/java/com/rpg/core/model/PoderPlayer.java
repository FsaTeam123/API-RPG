package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PoderPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PODER_PLAYER")
    private Long idPoderPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_PODER", nullable = false)
    private Poder poder;

    public PoderPlayer(Long idPoderPlayer) {
        this.idPoderPlayer = idPoderPlayer;
    }
}
