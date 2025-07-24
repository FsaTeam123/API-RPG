package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PericiaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERICIA_PLAYER")
    private Long idPericiaPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_PERICIA", nullable = false)
    private Pericia pericia;

    public PericiaPlayer(Long idPericiaPlayer) {
        this.idPericiaPlayer = idPericiaPlayer;
    }
}
