package com.rpg.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "BOLSA_PLAYER",
        // Unicidade separada: (player,item) e (player,arma)
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_BOLSA_ITEM", columnNames = {"ID_PLAYER","ID_ITENS"}),
                @UniqueConstraint(name = "UK_BOLSA_ARMA", columnNames = {"ID_PLAYER","ID_ARMA"})
        },
        indexes = {
                @Index(name = "IX_BP_PLAYER", columnList = "ID_PLAYER"),
                @Index(name = "IX_BP_ITEM",   columnList = "ID_ITENS"),
                @Index(name = "IX_BP_ARMA",   columnList = "ID_ARMA")
        })
@Check(constraints =
        "(ID_ITENS IS NOT NULL AND ID_ARMA IS NULL) OR (ID_ITENS IS NULL AND ID_ARMA IS NOT NULL)")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class BolsaPlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_BOLSA_PLAYER")
    @EqualsAndHashCode.Include
    private Long idBolsaPlayer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    @JsonIgnore
    private Player player;

    // Um OU outro (regra garantida pelo @Check acima)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ITENS")
    private Itens item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_ARMA")
    private Arma arma;

    @NotNull @Min(0)
    @Column(name = "QTD", nullable = false)
    private Integer qtd = 0;

    @NotNull @Min(0)
    @Column(name = "PESO", nullable = false)
    private Integer peso = 0;   // se for fracionário, use BigDecimal

    @NotNull @Min(0)
    @Column(name = "PRECO", nullable = false)
    private Integer preco = 0;  // preço: considere BigDecimal

    @Column(name = "DESCRICAO", length = 512)
    private String descricao;

    public BolsaPlayer(Long id) { this.idBolsaPlayer = id; }
}
