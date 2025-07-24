package com.rpg.core.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OficioJogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_OFICIO_JOGO")
    private Long idOficioJogo;

    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "ID_PLAYER", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "ID_JOGO", nullable = false)
    private Jogo jogo;

    public OficioJogo(Long idOficioJogo) {
        this.idOficioJogo = idOficioJogo;
    }
}
