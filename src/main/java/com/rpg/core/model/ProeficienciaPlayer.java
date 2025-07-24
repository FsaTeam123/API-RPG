package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProeficienciaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PROEFICIENCIA_PLAYER")
    private Long idProeficienciaPlayer;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_PROEFICIENCIA", nullable = false)
    private Proeficiencia proeficiencia;

    public ProeficienciaPlayer(Long idProeficienciaPlayer) {
        this.idProeficienciaPlayer = idProeficienciaPlayer;
    }
}
