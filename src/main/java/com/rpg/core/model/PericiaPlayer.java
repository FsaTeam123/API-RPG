package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PERICIA_PLAYER",
        uniqueConstraints = @UniqueConstraint(name = "UK_PERICIA_PLAYER",
                columnNames = {"ID_PLAYER","ID_PERICIA"}),
        indexes = {
                @Index(name = "IX_PRP_PLAYER",  columnList = "ID_PLAYER"),
                @Index(name = "IX_PRP_PERICIA", columnList = "ID_PERICIA")
        })
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PericiaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERICIA_PLAYER")
    @EqualsAndHashCode.Include
    private Long idPericiaPlayer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    @JsonIgnore
    private Player player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PERICIA", nullable = false)
    private Pericia pericia;

    public PericiaPlayer(Long id) { this.idPericiaPlayer = id; }
}
