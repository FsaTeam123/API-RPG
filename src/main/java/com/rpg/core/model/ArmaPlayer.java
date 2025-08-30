package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ARMA_PLAYER",
        uniqueConstraints = @UniqueConstraint(name = "UK_ARMA_PLAYER",
                columnNames = {"ID_PLAYER","ID_ARMA"}),
        indexes = {
                @Index(name = "IX_AP_PLAYER", columnList = "ID_PLAYER"),
                @Index(name = "IX_AP_ARMA",   columnList = "ID_ARMA")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArmaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ARMA_PLAYER")
    @EqualsAndHashCode.Include
    private Long idArmaPlayer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    @JsonIgnore
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_ARMA", nullable = false)
    private Arma arma;

    public ArmaPlayer(Long id) { this.idArmaPlayer = id; }
}
