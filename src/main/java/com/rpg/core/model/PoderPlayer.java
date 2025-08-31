package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PODER_PLAYER",
        uniqueConstraints = @UniqueConstraint(name = "UK_PODER_PLAYER",
                columnNames = {"ID_PLAYER","ID_PODER"}),
        indexes = {
                @Index(name = "IX_PP_PLAYER", columnList = "ID_PLAYER"),
                @Index(name = "IX_PP_PODER",  columnList = "ID_PODER")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PoderPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PODER_PLAYER")
    @EqualsAndHashCode.Include
    private Long idPoderPlayer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    @JsonIgnore
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PODER", nullable = false)
    private Poder poder;

    public PoderPlayer(Long id) { this.idPoderPlayer = id; }
}
