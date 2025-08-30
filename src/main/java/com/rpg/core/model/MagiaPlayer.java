package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "MAGIA_PLAYER",
        uniqueConstraints = @UniqueConstraint(name = "UK_MAGIA_PLAYER",
                columnNames = {"ID_PLAYER","ID_MAGIA"}),
        indexes = {
                @Index(name = "IX_MP_PLAYER", columnList = "ID_PLAYER"),
                @Index(name = "IX_MP_MAGIA",  columnList = "ID_MAGIA")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MagiaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MAGIA_PLAYER")
    @EqualsAndHashCode.Include
    private Long idMagiaPlayer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    @JsonIgnore
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_MAGIA", nullable = false)
    private Magia magia;

    public MagiaPlayer(Long id) { this.idMagiaPlayer = id; }
}
